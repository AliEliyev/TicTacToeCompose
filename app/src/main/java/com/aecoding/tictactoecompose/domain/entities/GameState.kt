package com.aecoding.tictactoecompose.domain.entities

data class GameState(
    var board: MutableList<MutableList<Char>>,
    var playerOne: Player = Player(
        playerName = "Player X",
        symbol = 'X'
    ),
    var playerTwo: Player = Player(
        playerName = "Player O",
        symbol = 'O'
    ),
    var currentPlayer: Player = playerOne,
    var winner: String = currentPlayer.playerName
)
