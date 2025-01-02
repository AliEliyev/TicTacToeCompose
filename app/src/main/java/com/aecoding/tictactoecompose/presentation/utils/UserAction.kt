package com.aecoding.tictactoecompose.presentation.utils

sealed class UserAction {
    data class makeMove(
        val row: Int,
        val col: Int
    ) : UserAction()
    object resetGame : UserAction()
}