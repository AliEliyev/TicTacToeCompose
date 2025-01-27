package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aecoding.tictactoecompose.core.GameScreenComponents
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.presentation.viewmodel.OnlineGameViewModel

@Composable
fun OnlineGameScreen(
    viewModel: OnlineGameViewModel = viewModel(),
    roomId: String,
    player: String,
    onNavigateToMenu: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getState(roomId,player)
        viewModel.startListening(roomId)
    }
    GameScreenComponents(
        viewModel = viewModel,
        onNavigateToMenu = onNavigateToMenu
    )

    DisposableEffect(Unit) {
        onDispose {
            if (viewModel.room.value.gameStatus != GameStatus.FINISHED){
                viewModel.setError()
            }
        }
    }
}