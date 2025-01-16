package com.aecoding.tictactoecompose.data.dto

data class PlayerDto(
    val playerName: String,
    val score: Int = 0,
    val symbol: String
)
