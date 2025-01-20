package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.aecoding.tictactoecompose.domain.entities.DialogState
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.presentation.utils.check
import com.aecoding.tictactoecompose.presentation.utils.isBoardFull
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class GameViewModel : ViewModel() {


    private val _gameState = MutableStateFlow(
        GameState(
            board = List(3) { List(3) { ' ' } }
        )
    )
    val gameState: StateFlow<GameState> = _gameState

    private fun check() {
        if (checkWinner()) {
            if (_gameState.value.winner == _gameState.value.playerOne.playerName) {
                _gameState.value = _gameState.value.copy(
                    playerOne = _gameState.value.playerOne.copy(
                        score = _gameState.value.playerOne.score + 1
                    )
                )
            } else if (_gameState.value.winner == _gameState.value.playerTwo.playerName) {
                _gameState.value = _gameState.value.copy(
                    playerTwo = _gameState.value.playerTwo.copy(
                        score = _gameState.value.playerTwo.score + 1
                    )
                )
            }

            if (_gameState.value.playerOne.score == 3) {
                _gameState.value = _gameState.value.copy(
                    dialogState = DialogState.ShowWinnerDialog
                )
                resetBoard()
            } else if (_gameState.value.playerTwo.score == 3) {
                _gameState.value = _gameState.value.copy(
                    dialogState = DialogState.ShowWinnerDialog
                )
                resetBoard()

            } else {
                resetBoard()
                _gameState.value = _gameState.value.copy(
                    dialogState = DialogState.ShowRoundDialog
                )
            }
        } else if (isBoardFull()) {
            _gameState.value = _gameState.value.copy(
                dialogState = DialogState.ShowDrawDialog
            )
            resetBoard()
        }
    }

    fun resetEffect() {
        _gameState.value = _gameState.value.copy(
            dialogState = null
        )
    }

    fun makeMove(
        row: Int,
        column: Int
    ) {
        val updatedBoard: List<List<Char>>

        if (_gameState.value.board[row][column] == ' ') {
            updatedBoard = _gameState.value.board.mapIndexed { r, rowList ->
                rowList.mapIndexed { c, cell ->
                    if (r == row && c == column) _gameState.value.currentPlayer.symbol else cell
                }
            }
            _gameState.value = _gameState.value.copy(
                board = updatedBoard
            )
            if (checkWinner()) {
                _gameState.value = _gameState.value.copy(
                    winner = _gameState.value.currentPlayer.playerName
                )
            }
            switchTurn()
            check()
        }
    }

    private fun switchTurn() {
        if (_gameState.value.currentPlayer == _gameState.value.playerOne) {
            _gameState.value = _gameState.value.copy(
                currentPlayer = _gameState.value.playerTwo
            )
        } else if (_gameState.value.currentPlayer == _gameState.value.playerTwo) {
            _gameState.value = _gameState.value.copy(
                currentPlayer = _gameState.value.playerOne
            )

        }
    }

    private fun resetBoard() {
        _gameState.value = _gameState.value.copy(
            board = List(3) { List(3) { ' ' } }
        )
    }

    fun resetGame() {
        _gameState.value = GameState(
            board = List(3) { List(3) { ' ' } }
        )
    }

    private fun checkWinner(): Boolean {
        return _gameState.value.board.check()
    }

    private fun isBoardFull(): Boolean {
        return _gameState.value.board.isBoardFull()
    }
}