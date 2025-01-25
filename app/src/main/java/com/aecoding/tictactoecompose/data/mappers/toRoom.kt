package com.aecoding.tictactoecompose.data.mappers

import com.aecoding.tictactoecompose.data.dto.GameStateDto
import com.aecoding.tictactoecompose.data.dto.PlayerDto
import com.aecoding.tictactoecompose.data.dto.RoomDto
import com.aecoding.tictactoecompose.domain.entities.DialogState
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
    val list = this.map {
        it.toList()
    }
    return list
}

fun String.toGameEffect(): DialogState? {
    return when (this) {
        "ShowWinnerDialog" -> DialogState.ShowWinnerDialog
        "ShowDrawDialog" -> DialogState.ShowDrawDialog
        "ShowRoundDialog" -> DialogState.ShowRoundDialog
        else -> null
    }
}

fun GameStateDto.toGameState(): GameState {
    return GameState(
        board = board.toNestedArr(),
        playerOne = playerOne.toPlayer(),
        playerTwo = playerTwo.toPlayer(),
        currentPlayer = currentPlayer.toPlayer(),
        winner = winner,
        dialogState = gameEffect.toGameEffect()
    )
}

fun String.toGameStatus(): GameStatus {
    return when (this) {
        "OFFLINE" -> GameStatus.OFFLINE
        "CREATED" -> GameStatus.CREATED
        "JOINED" -> GameStatus.JOINED
        "FINISHED" -> GameStatus.FINISHED
        else -> GameStatus.OFFLINE
    }
}

fun RoomDto.toRoom(): Room {
    return Room(
        roomId = roomId,
        gameStatus = gameStatus.toGameStatus(),
        gameState = gameState!!.toGameState()
    )
}