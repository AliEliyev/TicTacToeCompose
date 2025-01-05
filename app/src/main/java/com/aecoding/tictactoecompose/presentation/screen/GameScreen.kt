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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aecoding.tictactoecompose.presentation.utils.UserAction
import com.aecoding.tictactoecompose.presentation.viewmodel.GameViewModel
import com.aecoding.tictactoecompose.ui.theme.MainBg

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    val gameState by remember { mutableStateOf(viewModel.gameState) }
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
                    text = gameState.playerOne.playerName
                )
                Text(
                    text = gameState.playerOne.score.toString()
                )
            }
            Column(
                modifier = Modifier.height(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = gameState.playerTwo.playerName
                )
                Text(
                    text = gameState.playerTwo.score.toString()
                )
            }
        }
        viewModel.gameState.board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, text ->
                    GameBox(text) {
                        viewModel.onAction(UserAction.makeMove(rowIndex, colIndex))
                    }
                }
            }
        }

        if (viewModel.checkWinner()) {
            if (viewModel.gameState.winner == gameState.playerOne.playerName) {
                gameState.playerOne = gameState.playerOne.copy(
                    score = gameState.playerOne.score + 1
                )
            } else if (viewModel.gameState.winner == gameState.playerTwo.playerName) {
                gameState.playerTwo = gameState.playerTwo.copy(
                    score = gameState.playerTwo.score + 1
                )
            }
            RoundWinDialog(viewModel.gameState.winner) {
                viewModel.gameState = viewModel.resetGame()
            }
        } else if (viewModel.isBoardFull()) {
            DrawScreen { viewModel.gameState = viewModel.resetGame() }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${viewModel.gameState.currentPlayer.playerName} 's Turn",
                fontSize = 25.sp,
                lineHeight = 31.35.sp,
            )
        }
    }
}

