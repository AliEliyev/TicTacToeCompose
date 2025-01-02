package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.presentation.utils.Checker
import com.aecoding.tictactoecompose.presentation.utils.UserAction

class GameViewModel: ViewModel() {

    private var checker = Checker()
    var gameState by
    mutableStateOf(
        GameState(
            board = MutableList(3) { MutableList(3) { ' ' } }
        )
    )


    @Composable
    fun OnAction(action: UserAction) {
        when (action) {
            is UserAction.makeMove -> {
                MakeMove(action.row, action.col)
            }

            UserAction.resetGame -> {
                resetGame()
            }
        }
    }


    @Composable
    private fun MakeMove(
        row: Int,
        column: Int
    ) {
        if (gameState.board[row][column] == ' ') {
            gameState.board[row][column] = gameState.currentPlayer.symbol
            if (checkWinner()){
                gameState.winner = gameState.currentPlayer
            }
            switchTurn()

        }
    }

    private fun switchTurn() {
        if (gameState.currentPlayer == gameState.playerOne) {
            gameState = gameState.copy(
                currentPlayer = gameState.playerTwo
            )
        } else if (gameState.currentPlayer == gameState.playerTwo) {
            gameState = gameState.copy(
                currentPlayer = gameState.playerOne
            )

        }
    }

    fun resetGame(): GameState {
       return gameState.copy(
            board = MutableList(3){MutableList(3){' '} }
        )
    }

    fun checkWinner(): Boolean {
        return checker.checkWinner(gameState)
    }

    fun isBoardFull(): Boolean {
        return checker.isBoardFull(gameState)
    }
}