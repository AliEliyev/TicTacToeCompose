package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.Injection
import com.aecoding.tictactoecompose.data.RoomRepository
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.data.mappers.toDto
import com.aecoding.tictactoecompose.data.mappers.toRoom
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class WaitingViewModel(
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

    fun startListening(roomId: String) {
        viewModelScope.launch { listenForRoomUpdates(roomId) }
    }

    fun setStatus(status: GameStatus){
        _room.value = _room.value.copy(
            gameStatus = status
        )
        viewModelScope.launch {
            repository.saveRoom(_room.value.toDto())
        }
    }

    private fun listenForRoomUpdates(roomId: String) {
        repository.getRoomFlow(roomId).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _room.value = result.data.toRoom()
                }

                is Result.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}