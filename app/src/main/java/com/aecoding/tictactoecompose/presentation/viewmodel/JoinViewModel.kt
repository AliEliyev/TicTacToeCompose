package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.Injection
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.data.RoomRepository
import com.aecoding.tictactoecompose.data.mappers.toDto
import com.aecoding.tictactoecompose.data.mappers.toRoom
import com.aecoding.tictactoecompose.domain.entities.GameEffect
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Room
import com.aecoding.tictactoecompose.domain.entities.RoomError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JoinViewModel(
    private val repository: RoomRepository = RoomRepository(Injection.instance())
) : ViewModel() {
    private val _room = MutableStateFlow(
        Room(
            gameState = GameState(
                board = List(3) { List(3) { ' ' } }
            )
        )
    )
    val room: StateFlow<Room> = _room


    private val _roomErrors = MutableStateFlow(emptyList<RoomError>())
    val roomErrors: StateFlow<List<RoomError>> = _roomErrors

    var gameEffect = MutableStateFlow(GameEffect.NULL)

    fun joinRoom(roomId: String, playerTwo: String) {
        viewModelScope.launch {
            _room.value = _room.value.copy(
                roomId = roomId,
                gameStatus = GameStatus.JOINED,
                gameState = _room.value.gameState.copy(
                    playerTwo = _room.value.gameState.playerTwo.copy(
                        playerName = playerTwo
                    )
                )
            )
            repository.saveRoom(_room.value.toDto())
        }
    }

    fun idChecker(roomId: String) {
        viewModelScope.launch {
            when (val result = repository.fetchRoom(roomId)) {
                is Result.Error -> {
                    _roomErrors.value = emptyList()
                    if (!_roomErrors.value.contains(RoomError.INVALID_ID)) {
                        _roomErrors.value += RoomError.INVALID_ID
                    }
                    gameEffect.value = GameEffect.WRONGID
                }

                is Result.Success -> {
                    if (_roomErrors.value.contains(RoomError.INVALID_ID)) {
                        _roomErrors.value -= RoomError.INVALID_ID
                    }
                    if (result.data.toRoom().gameStatus == GameStatus.CREATED) {
                        _roomErrors.value = emptyList()
                        _room.value = result.data.toRoom()
                        gameEffect.value = GameEffect.NAVIGATE
                    } else if (result.data.toRoom().gameStatus == GameStatus.JOINED &&
                        !_roomErrors.value.contains(RoomError.ROOM_FULL)
                    ) {
                        _roomErrors.value = emptyList()
                        _roomErrors.value += RoomError.ROOM_FULL
                    } else if (result.data.toRoom().gameStatus == GameStatus.FINISHED &&
                        !_roomErrors.value.contains(RoomError.GAME_FINISHED)
                    ) {
                        _roomErrors.value = emptyList()
                        _roomErrors.value += RoomError.GAME_FINISHED
                    }
                }
            }
        }
    }
}