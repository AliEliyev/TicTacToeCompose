package com.aecoding.tictactoecompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.data.Injection
import com.aecoding.tictactoecompose.data.OnlineRepository
import com.aecoding.tictactoecompose.data.Result
import com.aecoding.tictactoecompose.data.mappers.toRoom
import com.aecoding.tictactoecompose.domain.entities.GameState
import com.aecoding.tictactoecompose.domain.entities.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WaitingViewModel(
    private val repository: OnlineRepository= OnlineRepository(Injection.instance())
) : ViewModel() {
    private val _room = MutableStateFlow(
        Room(
            gameState = GameState(
                board = List(3) { List(3) { ' ' } }
            )
        )
    )
    val room: StateFlow<Room> = _room



}