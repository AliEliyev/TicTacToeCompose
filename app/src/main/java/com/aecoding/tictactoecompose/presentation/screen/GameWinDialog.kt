package com.aecoding.tictactoecompose.presentation.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.aecoding.tictactoecompose.presentation.utils.ButtonText
import com.aecoding.tictactoecompose.presentation.utils.DialogText
import com.aecoding.tictactoecompose.ui.theme.MainBg

@Composable
fun GameWinDialog(
    showDialog: Boolean,
    winner: String,
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
            dismissButton = {},
            modifier = Modifier
                .background(MainBg)
                .padding(15.dp),
            text = {
                DialogText("$winner Won the Game!")
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