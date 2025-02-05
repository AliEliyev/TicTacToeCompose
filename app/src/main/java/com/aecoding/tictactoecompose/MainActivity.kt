package com.aecoding.tictactoecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.aecoding.tictactoecompose.presentation.nav.Navigation
import com.aecoding.tictactoecompose.ui.theme.TicTacToeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TicTacToeComposeTheme {
                Navigation(
                    navController = navController,
                )
            }
        }
    }
}
