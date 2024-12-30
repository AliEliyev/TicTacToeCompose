package com.aecoding.tictactoecompose.presentation.utils

import com.aecoding.tictactoecompose.domain.entities.GameState

class Listeners {
    fun makeMove(
        gameState: GameState,
        row: Int,
        column: Int
    ): GameState {
        if (gameState.board[row][column] == ' ') {
            gameState.board[row][column] = gameState.currentPlayer.symbol
        }


        return GameState(
            board = gameState.board,
            currentPlayer = gameState.currentPlayer.copy(
                symbol = if (gameState.currentPlayer.symbol == 'X') 'O' else 'X'
            )
        )
    }

    fun checkWinner(
        gameState: GameState
    ): Boolean {

        if (
            gameState.board[0][0] == gameState.board[1][1] &&
            gameState.board[1][1] == gameState.board[2][2] &&
            gameState.board[0][0] != ' '
        ) {
            return true
        } else if (
            gameState.board[0][2] == gameState.board[1][1] &&
            gameState.board[1][1] == gameState.board[2][0] &&
            gameState.board[0][2] != ' '
        ) {
            return true
        }
        for (i in 0..2) {
            if (gameState.board[i][0] == gameState.board[i][1] &&
                gameState.board[i][1] == gameState.board[i][2] &&
                gameState.board[i][0] != ' '
            ) {
                return true
            } else if (gameState.board[0][i] == gameState.board[1][i] &&
                gameState.board[1][i] == gameState.board[2][i] &&
                gameState.board[0][i] != ' '
            ) {
                return true
            }
        }
        return false
    }

    fun resetGame(gameState: GameState): GameState {
        return gameState.copy(
            board = MutableList(3) { MutableList(3) { ' ' } }
        )
    }
}