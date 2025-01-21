package com.aecoding.tictactoecompose.presentation.viewmodel

import com.aecoding.tictactoecompose.core.BaseGameViewModel
import com.aecoding.tictactoecompose.domain.entities.DialogState
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.presentation.utils.check
import com.aecoding.tictactoecompose.presentation.utils.isBoardFull

open class OfflineGameViewModel : BaseGameViewModel() {

    override fun getInitialState(): GameState {
        return GameState(
            board = List(3) { List(3) { ' ' } }
        )
    }

    override fun check() {
        if (checkWinner()) {
            if (gameState.value.winner == gameState.value.playerOne.playerName) {
                setState(
                    gameState.value.copy(
                        playerOne = gameState.value.playerOne.copy(
                            score = gameState.value.playerOne.score + 1
                        )
                    )
                )
            } else if (gameState.value.winner == gameState.value.playerTwo.playerName) {
                setState(
                    gameState.value.copy(
                        playerTwo = gameState.value.playerTwo.copy(
                            score = gameState.value.playerTwo.score + 1
                        )
                    )
                )
            }

            if (gameState.value.playerOne.score == 3) {
                setState(gameState.value.copy(dialogState = DialogState.ShowWinnerDialog))
                resetBoard()
            } else if (gameState.value.playerTwo.score == 3) {
                setState(gameState.value.copy(dialogState = DialogState.ShowWinnerDialog))
                resetBoard()

            } else {
                resetBoard()
                setState(gameState.value.copy(dialogState = DialogState.ShowRoundDialog))
            }
        } else if (isBoardFull()) {
            setState(gameState.value.copy(dialogState = DialogState.ShowDrawDialog))
            resetBoard()
        }
    }

    override fun resetEffect() {
        setState(gameState.value.copy(dialogState = null))
    }

    override fun makeMove(
        row: Int,
        column: Int
    ) {
        val updatedBoard: List<List<Char>>

        if (gameState.value.board[row][column] == ' ') {
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
            setState(gameState.value.copy(currentPlayer = gameState.value.playerTwo))
        } else if (gameState.value.currentPlayer == gameState.value.playerTwo) {
            setState(gameState.value.copy(currentPlayer = gameState.value.playerOne))
        }
    }

    override fun resetBoard() {
        setState(
            gameState.value.copy(
                board = List(3) { List(3) { ' ' } }
            )
        )
    }

    override fun resetGame() {
        setState( GameState(
            board = List(3) { List(3) { ' ' } }
        ))
    }

    override fun checkWinner(): Boolean {
        return gameState.value.board.check()
    }

    override fun isBoardFull(): Boolean {
        return gameState.value.board.isBoardFull()
    }
}