package com.aecoding.tictactoecompose.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aecoding.tictactoecompose.presentation.screen.CreateRoomScreen
import com.aecoding.tictactoecompose.presentation.screen.JoinRoomScreen
import com.aecoding.tictactoecompose.presentation.screen.MainScreen
import com.aecoding.tictactoecompose.presentation.screen.OfflineGameScreen
import com.aecoding.tictactoecompose.presentation.screen.OnlineGameScreen
import com.aecoding.tictactoecompose.presentation.screen.WaitingScreen

@Composable
fun Navigation(
    navController: NavController,
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.MenuScreen.route
    ) {
        composable(Screen.MenuScreen.route) {
            MainScreen(
                onNavigateToCreateRoom = { navController.navigate(Screen.CreateRoomScreen.route) },
                onNavigateToJoinRoom = { navController.navigate(Screen.JoinRoomScreen.route) },
                onNavigateToGameRoom = { navController.navigate(Screen.OfflineGameScreen.route) }
            )
        }
        composable(Screen.OfflineGameScreen.route) {
            OfflineGameScreen(
                onNavigateToMenu = { navController.navigate(Screen.MenuScreen.route) })
        }
        composable("${Screen.OnlineGameScreen.route}/{roomId}/{player}") {
            val roomId = it.arguments?.getString("roomId") ?: "-1"
            val player =  it.arguments?.getString("player") ?: "-1"
            OnlineGameScreen(
                roomId = roomId,
                player = player,
                onNavigateToMenu = { navController.navigate(Screen.MenuScreen.route) }
            )
        }
        composable(Screen.CreateRoomScreen.route) {
            CreateRoomScreen(
                onNavigateToWaiting = { navController.navigate("${Screen.WaitingScreen.route}/${it}") }
            )
        }
        composable("${Screen.WaitingScreen.route}/{roomId}") { navBackStackEntry ->
            val roomId = navBackStackEntry.arguments?.getString("roomId") ?: "-1"
            WaitingScreen(
                roomId = roomId,
                onNavigateToGame = {
                    navController.navigate("${Screen.OnlineGameScreen.route}/$it/1")
                })

        }
        composable(Screen.JoinRoomScreen.route) {
            JoinRoomScreen(
                onNavigateToGame = {
                    navController.navigate("${Screen.OnlineGameScreen.route}/$it/2")
                }
            )
        }
    }
}