package com.aecoding.tictactoecompose.presentation.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aecoding.tictactoecompose.R
import com.aecoding.tictactoecompose.presentation.utils.ButtonText
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.MainBg
import com.aecoding.tictactoecompose.ui.theme.TicTacToeComposeTheme

@Composable
fun WaitingScreen() {
    Column(
        modifier = Modifier
            .background(color = MainBg)
            .fillMaxSize()
            .padding(35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonText(
            modifier = Modifier.padding(20.dp),
            text = "Waiting for a player to join...",
            color = Color.White
        )
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .border(
                    color = BlueShadowColor.copy(0.5f),
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            ButtonText(
                text = "21315466",
                color = Color.White
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(50.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_content_copy_24),
                    contentDescription = "copy",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.White
                )
            }
        }


    }
}

@Preview
@Composable
private fun WaitingPrev() {
    TicTacToeComposeTheme {
        WaitingScreen()
    }
}