package com.aecoding.tictactoecompose.data.mappers

import com.aecoding.tictactoecompose.data.dto.GameStateDto
import com.aecoding.tictactoecompose.data.dto.PlayerDto
import com.aecoding.tictactoecompose.data.dto.RoomDto
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.Player
import com.aecoding.tictactoecompose.domain.entities.Room

fun Player.toDto(): PlayerDto {
    return PlayerDto(
        playerName = playerName,
        score = score,
        symbol = symbol.toString()
    )
}

fun List<List<Char>>.toDto(): List<String> {
    val stringList: List<String> = this.map {
        it.joinToString("")
    }
    return stringList
}

fun GameState.toDto(): GameStateDto {
    return GameStateDto(
        board = board.toDto(),
        playerOne = playerOne.toDto(),
        playerTwo = playerTwo.toDto(),
        gameEffect = dialogState.toString()
    )
}

fun Room.toDto(): RoomDto {
    return RoomDto(
        roomId = roomId,
        gameStatus = gameStatus.toString(),
        gameState = gameState.toDto()
    )
}

