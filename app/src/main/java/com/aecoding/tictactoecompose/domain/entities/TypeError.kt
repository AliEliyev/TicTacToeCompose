package com.aecoding.tictactoecompose.domain.entities

enum class TypeError(val text: String) {
    EMPTY_INPUT("Input cannot be empty."),
    ID_TOO_SHORT("ID is too short. Please enter at least 7 digits."),
    ID_NOT_NUMERIC("ID must contain only digits."),
}

enum class RoomError(val text: String) {
    ROOM_FULL("The room is full. Please try another room."),
    INVALID_ID("Invalid ID provided."),
    GAME_FINISHED("The game has already finished.")
}