package com.aecoding.tictactoecompose.presentation.nav

import android.util.Log
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
import com.aecoding.tictactoecompose.presentation.viewmodel.CreateViewModel
import com.aecoding.tictactoecompose.presentation.viewmodel.GameViewModel
import com.aecoding.tictactoecompose.presentation.viewmodel.JoinViewModel
import com.aecoding.tictactoecompose.presentation.viewmodel.WaitingViewModel

@Composable
fun Navigation(
    navController: NavController,
    gameViewModel: GameViewModel,
    createViewModel: CreateViewModel,
    joinViewModel: JoinViewModel,
    waitingViewModel: WaitingViewModel
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
        composable(Screen.GameScreen.route) {
            GameScreen(
                viewModel = gameViewModel,
                onNavigateToMenu = { navController.navigate(Screen.MenuScreen.route) })
        }
        composable(Screen.CreateRoomScreen.route) {
            CreateRoomScreen(
                createViewModel = createViewModel,
                onNavigateToWaiting = { navController.navigate("${Screen.WaitingScreen.route}/${it}") }
            )
        }
        composable("${Screen.WaitingScreen.route}/{gameId}") {
               val gameId: String = it.arguments?.getString("gameId") ?: "-1"
               WaitingScreen(
                   createViewModel = createViewModel,
                   gameId = gameId,
                   onNavigateToGame = { navController.navigate(Screen.GameScreen.route) })

        }
        composable(Screen.JoinRoomScreen.route) {
            JoinRoomScreen(
                joinViewModel = joinViewModel,
                onNavigateToGame = { navController.navigate(Screen.GameScreen.route) }
            )
        }
    }
}