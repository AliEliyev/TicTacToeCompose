package com.aecoding.tictactoecompose.data.mappers

import com.aecoding.tictactoecompose.data.dto.GameStateDto
import com.aecoding.tictactoecompose.data.dto.PlayerDto
import com.aecoding.tictactoecompose.data.dto.RoomDto
import com.aecoding.tictactoecompose.domain.entities.GameEffect
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Player
import com.aecoding.tictactoecompose.domain.entities.Room

fun PlayerDto.toPlayer(): Player {
    return Player(
        playerName = playerName,
        score = score,
        symbol = if (symbol == "X") 'X' else 'O'
    )
}

fun List<String>.toNestedArr(): List<List<Char>> {
    val list = this.mapIndexed { _, s ->
        s.toList()
    }
    return list
}

fun String.toGameEffect(): GameEffect? {
    return when (this) {
        "GameEffect.ShowWinnerDialog" -> GameEffect.ShowWinnerDialog
        "GameEffect.ShowDrawDialog" -> GameEffect.ShowDrawDialog
        "GameEffect.ShowRoundDialog" -> GameEffect.ShowRoundDialog
        else -> null
    }
}

fun GameStateDto.toGameState(): GameState {
    return GameState(
        board = board.toNestedArr(),
        playerOne = playerOne.toPlayer(),
        playerTwo = playerTwo.toPlayer(),
        gameEffect = gameEffect.toGameEffect()
    )
}

fun String.toGameStatus(): GameStatus {
    return when (this) {
        "GameStatus.OFFLINE" -> GameStatus.OFFLINE
        "GameStatus.CREATED" -> GameStatus.CREATED
        "GameStatus.JOINED" -> GameStatus.JOINED
        "GameStatus.FINISHED" -> GameStatus.FINISHED
        else -> GameStatus.OFFLINE
    }
}

fun RoomDto.toRoom(): Room {
    return Room(
        roomId = roomId,
        gameStatus =gameStatus.toGameStatus(),
        gameState = gameState!!.toGameState()
    )
}