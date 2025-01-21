package com.aecoding.tictactoecompose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aecoding.tictactoecompose.domain.entities.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseGameViewModel : ViewModel() {

    abstract fun getInitialState(): GameState
    protected abstract fun check()
    abstract fun resetEffect()
    abstract fun makeMove(row: Int, column: Int)
    protected abstract fun switchTurn()
    protected abstract fun resetBoard()
    abstract fun resetGame()
    protected abstract fun checkWinner(): Boolean
    protected abstract fun isBoardFull(): Boolean

    private val _gameState = MutableStateFlow(
        this.getInitialState()
    )
    val gameState = _gameState.asStateFlow()

    protected fun setState(newState: GameState) {
        viewModelScope.launch {
            _gameState.value = newState
        }
    }


}