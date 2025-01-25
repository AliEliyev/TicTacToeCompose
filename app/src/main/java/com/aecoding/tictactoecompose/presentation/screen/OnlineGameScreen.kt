package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aecoding.tictactoecompose.core.GameScreenComponents
import com.aecoding.tictactoecompose.presentation.viewmodel.OnlineGameViewModel

@Composable
fun OnlineGameScreen(
    viewModel: OnlineGameViewModel = viewModel(),
    roomId: String,
    onNavigateToMenu: () -> Unit
) {
    viewModel.getState(roomId)

    LaunchedEffect(Unit) {
        viewModel.startListening(roomId)
    }
    GameScreenComponents(
        viewModel = viewModel,
        onNavigateToMenu = onNavigateToMenu
    )
}