package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.presentation.screen.DrawScreen
import com.aecoding.tictactoecompose.presentation.screen.GameWinDialog
import com.aecoding.tictactoecompose.presentation.screen.RoundWinDialog
import com.aecoding.tictactoecompose.presentation.utils.Checker
import com.aecoding.tictactoecompose.presentation.utils.UserAction

class GameViewModel : ViewModel() {

    private var checker = Checker()
    var gameState by
    mutableStateOf(
        GameState(
            board = MutableList(3) { MutableList(3) { ' ' } }
        )
    )

    @Composable
    fun Check(
        onNavigateToMenu: () -> Unit,
    ){

        if (checkWinner()) {
            if (gameState.winner == gameState.playerOne.playerName) {
                gameState.playerOne = gameState.playerOne.copy(
                    score = gameState.playerOne.score + 1
                )
            } else if (gameState.winner == gameState.playerTwo.playerName) {
                gameState.playerTwo = gameState.playerTwo.copy(
                    score = gameState.playerTwo.score + 1
                )
            }

            if (gameState.playerOne.score == 3) {
                GameWinDialog(gameState.playerOne.playerName){
                    gameState = resetGame()
                    onNavigateToMenu()
                }
            } else if (gameState.playerTwo.score == 3) {
                GameWinDialog(gameState.playerTwo.playerName){
                    gameState = resetGame()
                    onNavigateToMenu()
                }
            } else {
                RoundWinDialog(gameState.winner) {
                    gameState = resetBoard()
                }
            }
        } else if (isBoardFull()) {
            DrawScreen { gameState = resetBoard() }
        }
    }


    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.makeMove -> {
                makeMove(action.row, action.col)
            }

            UserAction.resetGame -> {
                resetBoard()
            }
        }
    }


    private fun makeMove(
        row: Int,
        column: Int
    ) {
        if (gameState.board[row][column] == ' ') {
            gameState.board[row][column] = gameState.currentPlayer.symbol
            if (checkWinner()) {
                gameState.winner = gameState.currentPlayer.playerName
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

    fun resetBoard(): GameState {
        return gameState.copy(
            board = MutableList(3) { MutableList(3) { ' ' } }
        )
    }

    fun resetGame(): GameState{
        return GameState(
            board = MutableList(3) { MutableList(3) { ' ' } }
        )
    }

    fun checkWinner(): Boolean {
        return checker.checkWinner(gameState)
    }

    fun isBoardFull(): Boolean {
        return checker.isBoardFull(gameState)
    }
}