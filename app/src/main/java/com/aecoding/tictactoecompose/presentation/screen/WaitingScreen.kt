package com.aecoding.tictactoecompose.presentation.screen


import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aecoding.tictactoecompose.R
import com.aecoding.tictactoecompose.domain.entities.GameStatus
import com.aecoding.tictactoecompose.presentation.utils.ButtonText
import com.aecoding.tictactoecompose.presentation.viewmodel.WaitingViewModel
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.MainBg

@Composable
fun WaitingScreen(
    waitingViewModel: WaitingViewModel = viewModel(),
    roomId: String,
    onNavigateToGame: () -> Unit
) {
    val copy = remember { mutableStateOf(false) }
    val state = waitingViewModel.room.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        waitingViewModel.startListening(roomId)
    }

    LaunchedEffect(state.value.gameStatus) {
        if (state.value.gameStatus == GameStatus.JOINED) {
            onNavigateToGame()
        }
    }

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
                text = roomId,
                color = Color.White
            )
            IconButton(
                onClick = {
                    copy.value = true
                },
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
            if (copy.value) {
                CopyToClipboard(roomId)
                copy.value = false
            }
        }
    }
}


@Composable
fun CopyToClipboard(text: String, message: String = "Copied to clipboard!") {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    clipboardManager.setText(AnnotatedString(text)) // Copy text to clipboard
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show() // Optional: Show a toast message
}
