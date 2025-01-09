package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aecoding.tictactoecompose.domain.entities.GameEffect
import com.aecoding.tictactoecompose.presentation.viewmodel.GameViewModel
import com.aecoding.tictactoecompose.ui.theme.MainBg

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onNavigateToMenu: () -> Unit,
) {
    val gameState = viewModel.gameState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .background(
                color = MainBg
            )
            .fillMaxSize()
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.height(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = gameState.value.playerOne.playerName
                )
                Text(
                    text = gameState.value.playerOne.score.toString()
                )
            }
            Column(
                modifier = Modifier.height(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = gameState.value.playerTwo.playerName
                )
                Text(
                    text = gameState.value.playerTwo.score.toString()
                )
            }
        }
        gameState.value.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, text ->
                    GameBox(text) {
                        viewModel.makeMove(rowIndex, colIndex)
                    }
                }
            }
        }
        viewModel.check()
        when(gameState.value.gameEffect){
            GameEffect.ShowWinnerDialog -> {
                GameWinDialog(gameState.value.winner) {
                    onNavigateToMenu()
                    viewModel.resetGame()
                    viewModel.resetEffect()
                }
            }
            GameEffect.ShowRoundDialog -> {
                RoundWinDialog(
                    showDialog = true,
                    winner = gameState.value.winner) {
                    viewModel.resetEffect()
                }
            }
            GameEffect.ShowDrawDialog -> {
                DrawScreen(true){
                    viewModel.resetEffect()
                }
            }
            null -> {}
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${gameState.value.currentPlayer.playerName} 's Turn",
                fontSize = 25.sp,
                lineHeight = 31.35.sp,
            )
        }
    }
}

