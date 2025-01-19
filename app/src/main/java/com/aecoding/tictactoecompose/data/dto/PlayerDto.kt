package com.aecoding.tictactoecompose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    val playerName: String = "",
    val score: Int = 0,
    val symbol: String = ""
)
