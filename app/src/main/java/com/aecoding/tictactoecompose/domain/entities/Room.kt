package com.aecoding.tictactoecompose.domain.entities

data class Room(
    var roomId: String = "-1",
    var gameStatus: GameStatus = GameStatus.OFFLINE,
    var gameState: GameState
)

enum class GameStatus {
    OFFLINE,
    CREATED,
    JOINED,
    FINISHED
}