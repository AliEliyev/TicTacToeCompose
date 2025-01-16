package com.aecoding.tictactoecompose.data.dto

import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus

data class RoomDto(
    var roomId: String,
    var gameStatus: String,
    var gameState: GameStateDto
)
