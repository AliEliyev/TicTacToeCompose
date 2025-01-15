package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.OnlineRepository
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.domain.entities.Room
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnlineViewModel : ViewModel
    () {
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
                onlineRepository.saveRoom(_room.value)
            }
        }
    }

    fun joinRoom(gameId: String, playerTwo: String){
        if (gameId == _room.value.roomId){
            _room.value = _room.value.copy(
                gameStatus = GameStatus.JOINED,
                gameState = _room.value.gameState.copy(
                    playerTwo = _room.value.gameState.playerTwo.copy(
                        playerName = playerTwo
                    )
                )
            )
            viewModelScope.launch {
                onlineRepository.saveRoom(_room.value)
            }
        }
    }

    fun loadRoom(){
        viewModelScope.launch {
            //_room.value = onlineRepository.fetchRoom(_room.value)
            when(val result = onlineRepository.fetchRoom(_room.value)){
                is Result.Error -> TODO()
                is Result.Success -> _room.value = result.data
            }
        }
    }

}