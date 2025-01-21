package com.aecoding.tictactoecompose.presentation.nav

sealed class Screen(
    val route: String
) {
    data object MenuScreen : Screen(
        "menu_screen"
    )

    data object GameScreen : Screen(
        "game_screen"
    )

    data object CreateRoomScreen : Screen(
        "create_room_screen"
    )

    data object JoinRoomScreen : Screen(
        "join_room_screen"
    )

    data object WaitingScreen : Screen(
        "wait_screen"
    )
}