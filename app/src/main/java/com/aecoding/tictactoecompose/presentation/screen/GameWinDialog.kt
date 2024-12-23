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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.aecoding.tictactoecompose.ui.theme.MainBg
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.YellowShadowColor

@Composable
fun GameWinDialog(
    winner: String
) {
    val dialogOpen = remember { mutableStateOf(true) }
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                    }
                ) {
                    Text("Back to the Menu")
                }
            },
            dismissButton = {},
            modifier = Modifier
                .background(MainBg)
                .padding(15.dp),
            text = {
                Text(
                    text = "$winner Won the Game!",
                    fontSize = 25.sp,
                    lineHeight = 31.35.sp,
                    modifier = Modifier.wrapContentHeight(),
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = Orbitron,
                        shadow = Shadow(
                            color = YellowShadowColor,
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