package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.OnlineRepository
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.data.mappers.toDto
import com.aecoding.tictactoecompose.data.mappers.toRoom
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Room
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnlineViewModel : GameViewModel() {
    private val _room = MutableStateFlow(
        Room(
            gameState = GameState(
                board = List(3) { List(3) { ' ' } }
            )
        )
    )
    val room: StateFlow<Room> = _room
    private val onlineRepository: OnlineRepository by lazy {
        val instance = FirebaseFirestore.getInstance()
        OnlineRepository(instance)
    }
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading


    fun createRoom(gameId: String, playerOne: String) {
        if (gameId != "-1") {
            _room.value = _room.value.copy(
                roomId = gameId,
                gameStatus = GameStatus.CREATED,
                gameState = _room.value.gameState.copy(
                    playerOne = _room.value.gameState.playerOne.copy(
                        playerName = playerOne
                    )
                )
            )
            viewModelScope.launch {
                onlineRepository.saveRoom(_room.value.toDto())
            }
        }
    }

    fun joinRoom(roomId: String, playerTwo: String) {
        _room.value = _room.value.copy(
            roomId = roomId,
            gameStatus = GameStatus.JOINED,
            gameState = _room.value.gameState.copy(
                playerTwo = _room.value.gameState.playerTwo.copy(
                    playerName = playerTwo
                )
            )
        )
        viewModelScope.launch {
            onlineRepository.saveRoom(_room.value.toDto())
        }
    }

    fun loadRoom(roomId: String) {
        viewModelScope.launch {
            when (val result = onlineRepository.fetchRoom(roomId)) {
                is Result.Error -> _loading.value = false
                is Result.Success -> {
                    _room.value = result.data.toRoom()
                    _loading.value = true
                }
            }
        }
    }

    fun listenForRoomUpdates(onRoomUpdated: () -> Unit) {
        onlineRepository.listenForRoomUpdates(
            roomId = _room.value.roomId,
            onRoomUpdated = { roomDto ->
                _room.value = roomDto.toRoom()
                onRoomUpdated()
            }
        )
    }

    fun stopListeningForRoomUpdates() {
        onlineRepository.stopListeningForRoomUpdates()
    }

}