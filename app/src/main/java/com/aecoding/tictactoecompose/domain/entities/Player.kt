package com.aecoding.tictactoecompose.domain.entities

data class Player(
    val playerName: String,
    val score: Int = 0,
    val symbol: Char
)
