package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.aecoding.tictactoecompose.presentation.utils.ButtonText
import com.aecoding.tictactoecompose.ui.theme.MainBg
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.TicTacToeComposeTheme

@Composable
fun ErrorDialog(
    showDialog: Boolean,
    onClick: () -> Unit
) {
    val dialogOpen = remember { mutableStateOf(showDialog) }
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = { onClick() }
                ) {
                    ButtonText(
                        text = "Back to the Menu",
                        color = Color.White
                    )
                }
            },
            modifier = Modifier
                .background(MainBg)
                .padding(15.dp),
            text = {
                Text(
                    text = "Opponent Leave the Game.",
                    fontSize = 25.sp,
                    modifier = Modifier.wrapContentHeight(),
                    color = Color.Red,
                    style = TextStyle(
                        fontFamily = Orbitron,
                        shadow = Shadow(
                            color = Color.Red,
                            offset = Offset(-3f, -3f),
                            blurRadius = 25f
                        )
                    )
                )
            },
            shape = RoundedCornerShape(4.dp),
            backgroundColor = MainBg,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TicTacToeComposeTheme {
        ErrorDialog(
            true
        ) { }
    }
}