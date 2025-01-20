package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.Injection
import com.aecoding.tictactoecompose.data.OnlineRepository
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

class CreateViewModel(
    private val repository: OnlineRepository = OnlineRepository(Injection.instance())
):ViewModel() {
    private val _room = MutableStateFlow(
        Room(
            gameState = GameState(
                board = List(3) { List(3) { ' ' } }
            )
        )
    )
    val room: StateFlow<Room> = _room

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
                repository.saveRoom(_room.value.toDto())
            }
        }
    }

//    fun listenForRoomUpdates(onRoomUpdated: () -> Unit) {
//        repository.listenForRoomUpdates(
//            roomId = _room.value.roomId,
//            onRoomUpdated = { roomDto ->
//                _room.value = roomDto.toRoom()
//                onRoomUpdated()
//            }
//        )
//    }

    fun listenForRoomUpdates() {
        repository.getRoomFlow(_room.value.roomId).onEach {result ->
            when(result){
                is Result.Success -> {
                    _room.value = result.data.toRoom()
                }
                is Result.Error -> TODO()
            }
        }.launchIn(viewModelScope)
    }
}