package com.aecoding.tictactoecompose.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aecoding.tictactoecompose.presentation.screen.CreateRoomScreen
import com.aecoding.tictactoecompose.presentation.screen.GameScreen
import com.aecoding.tictactoecompose.presentation.screen.JoinRoomScreen
import com.aecoding.tictactoecompose.presentation.screen.MainScreen
import com.aecoding.tictactoecompose.presentation.screen.WaitingScreen
import com.aecoding.tictactoecompose.presentation.viewmodel.GameViewModel

@Composable
fun Navigation(
    navController: NavController,
    gameViewModel: GameViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.MenuScreen.route
    ) {
        composable(Screen.MenuScreen.route) {
            MainScreen(
                onNavigateToCreateRoom = { navController.navigate(Screen.CreateRoomScreen.route) },
                onNavigateToJoinRoom = { navController.navigate(Screen.JoinRoomScreen.route) },
                onNavigateToGameRoom = { navController.navigate(Screen.GameScreen.route) }
            )
        }
        composable(Screen.WaitingScreen.route) { WaitingScreen() }
        composable(Screen.GameScreen.route) {
            GameScreen(
                viewModel = gameViewModel,
                onNavigateToMenu = { navController.navigate(Screen.MenuScreen.route) })
        }
        composable(Screen.CreateRoomScreen.route) { CreateRoomScreen() }
        composable(Screen.JoinRoomScreen.route) { JoinRoomScreen() }
    }
}