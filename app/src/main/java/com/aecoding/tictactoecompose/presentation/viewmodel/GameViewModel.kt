package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.Player
import com.aecoding.tictactoecompose.presentation.utils.Listeners

class GameViewModel(
    //playerOne: Player,
    //var playerTwo: Player,
    //private val listener: Listeners
) : ViewModel() {
    var playerOne = Player(
        playerName = "P1",
        symbol = 'X'
    )
    var playerTwo = Player(
        playerName = "P2",
        symbol = 'O'
    )
    var listeners = Listeners()
    private val _gameState =
        mutableStateOf(
            GameState(
                board = MutableList(3) { MutableList(3) { ' ' } },
                currentPlayer = playerOne
            )
        )
    val gameState: MutableState<GameState> get() = _gameState

    //


    fun makeMove(
        row: Int,
        column: Int
    ) {
        _gameState.value = listeners.makeMove(gameState.value, row, column)
    }

    fun switchTurn() {
        if (_gameState.value.currentPlayer == playerOne) {
            _gameState.value.currentPlayer = playerTwo
        }
    }

    fun resetGame() {
        _gameState.value = listeners.resetGame(_gameState.value)
    }

    fun checkWinner(): Boolean {
        return listeners.checkWinner(_gameState.value)
    }
}