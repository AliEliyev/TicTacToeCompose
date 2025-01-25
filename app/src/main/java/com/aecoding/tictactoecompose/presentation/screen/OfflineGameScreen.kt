package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aecoding.tictactoecompose.core.GameScreenComponents
import com.aecoding.tictactoecompose.presentation.viewmodel.OfflineGameViewModel

@Composable
fun OfflineGameScreen(
    viewModel: OfflineGameViewModel = viewModel(),
    onNavigateToMenu: () -> Unit,
) {
    GameScreenComponents(
        viewModel = viewModel,
        onNavigateToMenu = onNavigateToMenu
    )
}

