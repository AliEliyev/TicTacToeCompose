package com.aecoding.tictactoecompose.domain.entities

data class GameState(
    var board: List<List<Char>>,
    var playerOne: Player = Player(
        playerName = "Player X",
        symbol = 'X'
    ),
    var playerTwo: Player = Player(
        playerName = "Player O",
        symbol = 'O'
    ),
    var currentPlayer: Player = playerOne,
    var winner: String = currentPlayer.playerName,
    var gameEffect: GameEffect? = null
)


sealed class GameEffect {
    data object ShowWinnerDialog : GameEffect()
    data object ShowRoundDialog : GameEffect()
    data object ShowDrawDialog : GameEffect()
}
