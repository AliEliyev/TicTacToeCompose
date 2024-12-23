package com.aecoding.tictactoecompose.domain.entities

data class GameState(
    val board: List<List<Char>>,
    val currentPlayer: String,
    val isGameOver: Boolean = false,
    val winner: Char? = null
)
