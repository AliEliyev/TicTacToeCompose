package com.aecoding.tictactoecompose.data.dto

import com.aecoding.tictactoecompose.domain.entities.GameEffect
import com.aecoding.tictactoecompose.domain.entities.Player

data class GameStateDto(
    var board: List<String>,
    var playerOne: PlayerDto,
    var playerTwo: PlayerDto,
    var currentPlayer: PlayerDto = playerOne,
    var winner: String = currentPlayer.playerName,
    var gameEffect: String? = null
)
