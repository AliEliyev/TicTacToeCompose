package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.Injection
import com.aecoding.tictactoecompose.data.RoomRepository
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.data.mappers.toDto
import com.aecoding.tictactoecompose.data.mappers.toRoom
import com.aecoding.tictactoecompose.domain.entities.GameEffect
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Room
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
    private val _isValidId = MutableStateFlow(true)
    val isValidId: StateFlow<Boolean> = _isValidId
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
                    _isValidId.value = false
                    gameEffect.value = GameEffect.WRONGID
                }

                is Result.Success -> {
                    _room.value = result.data.toRoom()
                    _isValidId.value = true
                    gameEffect.value = GameEffect.NAVIGATE
                }
            }
        }
    }
}