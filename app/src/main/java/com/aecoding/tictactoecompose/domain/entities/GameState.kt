package com.aecoding.tictactoecompose.domain.entities

data class GameState(
    var board: MutableList<MutableList<Char>>,
    var currentPlayer: Player,
    var isGameOver: Boolean = false,
)
