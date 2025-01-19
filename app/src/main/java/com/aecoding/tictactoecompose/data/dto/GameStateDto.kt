package com.aecoding.tictactoecompose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameStateDto(
    var board: List<String> = listOf(""),
    var playerOne: PlayerDto = PlayerDto(),
    var playerTwo: PlayerDto = PlayerDto(),
    var currentPlayer: PlayerDto = playerOne,
    var winner: String = currentPlayer.playerName,
    var gameEffect: String = ""
)
