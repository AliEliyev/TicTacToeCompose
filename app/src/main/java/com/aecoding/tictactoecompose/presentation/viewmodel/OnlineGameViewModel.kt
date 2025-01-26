package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.core.BaseGameViewModel
import com.aecoding.tictactoecompose.data.GameRepository
import com.aecoding.tictactoecompose.data.Injection
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.data.dto.RoomDto
import com.aecoding.tictactoecompose.data.mappers.toDto
import com.aecoding.tictactoecompose.data.mappers.toGameState
import com.aecoding.tictactoecompose.data.mappers.toRoom
import com.aecoding.tictactoecompose.domain.entities.DialogState
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Player
import com.aecoding.tictactoecompose.domain.entities.Room
import com.aecoding.tictactoecompose.presentation.utils.check
import com.aecoding.tictactoecompose.presentation.utils.isBoardFull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OnlineGameViewModel(
    private val repository: GameRepository = GameRepository(Injection.instance())
) : BaseGameViewModel() {
    private var _roomId = mutableStateOf("")
    private lateinit var _room: Room
    private lateinit var _player: Player

    fun getState(roomId: String,player: String) {
        viewModelScope.launch {
            _roomId.value = roomId
            when (val result = repository.fetchRoom(roomId)) {
                is Result.Error -> {}
                is Result.Success -> {
                    setState(result.data.toRoom().gameState)
                    setPlayer(player)
                    _room = result.data.toRoom()
                }
            }
        }
    }

    private fun setPlayer(player: String){
        if (player=="1"){
            _player = gameState.value.playerOne
        }else if(player=="2"){
            _player = gameState.value.playerTwo
        }
    }

    private fun sendState(
        state: GameState,
        gameStatus: GameStatus = GameStatus.JOINED) {
        setState(state)
        _room = _room.copy(
            gameState = state,
            gameStatus = gameStatus
        )
        viewModelScope.launch {
            repository.sendState(_room.toDto())
        }
    }

    override fun getInitialState(): GameState {
        return GameState(
            board = List(3) { List(3) { ' ' } }
        )
    }

    override fun check() {
        if (checkWinner()) {
            if (gameState.value.winner == gameState.value.playerOne.playerName) {
                sendState(
                    gameState.value.copy(
                        playerOne = gameState.value.playerOne.copy(
                            score = gameState.value.playerOne.score + 1
                        )
                    )
                )
            } else if (gameState.value.winner == gameState.value.playerTwo.playerName) {
                sendState(
                    gameState.value.copy(
                        playerTwo = gameState.value.playerTwo.copy(
                            score = gameState.value.playerTwo.score + 1
                        )
                    )
                )
            }

            if (gameState.value.playerOne.score == 3) {
                sendState(gameState.value.copy(dialogState = DialogState.ShowWinnerDialog))
                resetBoard()
            } else if (gameState.value.playerTwo.score == 3) {
                sendState(gameState.value.copy(dialogState = DialogState.ShowWinnerDialog))
                resetBoard()
            } else {
                resetBoard()
                sendState(gameState.value.copy(dialogState = DialogState.ShowRoundDialog))
            }
        } else if (isBoardFull()) {
            sendState(gameState.value.copy(dialogState = DialogState.ShowDrawDialog))
            resetBoard()
        }
    }

    override fun resetEffect() {
        sendState(gameState.value.copy(dialogState = null))
    }

    override fun makeMove(row: Int, column: Int) {
        val updatedBoard: List<List<Char>>

        if (gameState.value.board[row][column] == ' ' && _player.playerName == gameState.value.currentPlayer.playerName) {
            updatedBoard = gameState.value.board.mapIndexed { r, rowList ->
                rowList.mapIndexed { c, cell ->
                    if (r == row && c == column) gameState.value.currentPlayer.symbol else cell
                }
            }
            setState(gameState.value.copy(board = updatedBoard))
            if (checkWinner()) {
                setState(gameState.value.copy(winner = gameState.value.currentPlayer.playerName))
            }
            switchTurn()
            check()
        }
    }

    override fun switchTurn() {
        if (gameState.value.currentPlayer == gameState.value.playerOne) {
            sendState(gameState.value.copy(currentPlayer = gameState.value.playerTwo))
        } else if (gameState.value.currentPlayer == gameState.value.playerTwo) {
            sendState(gameState.value.copy(currentPlayer = gameState.value.playerOne))
        }
    }

    override fun resetBoard() {
        sendState(
            gameState.value.copy(
                board = List(3) { List(3) { ' ' } }
            ),
            gameStatus = GameStatus.FINISHED
        )
    }

    override fun resetGame() {
        sendState(GameState(
            board = List(3) { List(3) { ' ' } },
            dialogState = DialogState.BackToTheMenu
        ))

    }

    override fun checkWinner(): Boolean {
        return gameState.value.board.check()
    }

    override fun isBoardFull(): Boolean {
        return gameState.value.board.isBoardFull()
    }

    fun startListening(roomId: String) {
        viewModelScope.launch { listenForGameUpdates(roomId) }
    }

    private fun listenForGameUpdates(roomId: String) {
        repository.getRoomFlow(roomId).onEach { result ->
            when (result) {
                is Result.Success -> {
                    result.data?.let {
                        if (checkState(it.toGameState())) {
                            setState(it.toGameState())
                        }
                    }
                }
                is Result.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun checkState(state: GameState): Boolean{
        if (state.board!=gameState.value.board){
            return true
        }else if (state.playerOne!=gameState.value.playerOne){
            return true
        }else if (state.playerTwo!=gameState.value.playerTwo){
            return true
        }else if (state.dialogState!=gameState.value.dialogState){
            return true
        }else if (state.currentPlayer!=gameState.value.currentPlayer){
            return true
        }else if (state.winner!=gameState.value.winner){
            return true
        }
        return false
    }
}

