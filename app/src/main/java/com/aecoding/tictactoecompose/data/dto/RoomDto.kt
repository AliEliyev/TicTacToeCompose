package com.aecoding.tictactoecompose.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RoomDto(
    var roomId: String = "",
    var gameStatus: String = "",
    var gameState: GameStateDto? = null
)