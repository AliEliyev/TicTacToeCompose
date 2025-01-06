package com.aecoding.tictactoecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.aecoding.tictactoecompose.presentation.nav.Navigation
import com.aecoding.tictactoecompose.presentation.viewmodel.GameViewModel
import com.aecoding.tictactoecompose.ui.theme.TicTacToeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val gameViewModel = viewModel<GameViewModel>()
            TicTacToeComposeTheme {
                Navigation(
                    navController = navController,
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}
