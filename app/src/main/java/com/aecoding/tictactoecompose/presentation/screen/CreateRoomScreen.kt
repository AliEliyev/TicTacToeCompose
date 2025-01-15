package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aecoding.tictactoecompose.data.OnlineRepository
import com.aecoding.tictactoecompose.presentation.utils.ButtonText
import com.aecoding.tictactoecompose.presentation.utils.HeaderText
import com.aecoding.tictactoecompose.presentation.utils.buttonShadow
import com.aecoding.tictactoecompose.presentation.viewmodel.OnlineViewModel
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.MainBg
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.PlaceholderColor
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun CreateRoomScreen(
    onNavigateToWaiting: (String) -> Unit
) {
    val text = remember { mutableStateOf("") }
    var isEmpty by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(
                color = MainBg
            )
            .fillMaxSize()
            .padding(35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(330.dp)
                .height(180.dp)
        ) {
            HeaderText(
                text = "Create Room",
                textColor = Color.White,
                shadow = Shadow(
                    color = BlueShadowColor,
                    offset = Offset(3f, 3f),
                    blurRadius = 25f
                )
            )
            HeaderText(
                text = "Create Room",
                textColor = Color.White,
                shadow = Shadow(
                    color = BlueShadowColor,
                    offset = Offset(-3f, -3f),
                    blurRadius = 25f
                )
            )
        }

        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                fontFamily = Orbitron,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                lineHeight = 22.57.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            ),
            supportingText = {
                if (isEmpty) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Name can't be empty",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            placeholder = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ButtonText(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Enter your nickname",
                        color = PlaceholderColor
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlueShadowColor,
                unfocusedBorderColor = BlueShadowColor.copy(alpha = 0.5f),
            )
        )

        Button(
            onClick = {
                isEmpty = text.value.isEmpty()
                if (!isEmpty){
                    val gameId = Random.nextInt(1000000..9999999).toString()
                    onNavigateToWaiting(gameId)
                }
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(50.dp)
                .buttonShadow(
                    color = BlueShadowColor,
                    borderRadius = 10.dp,
                    blurRadius = 3.dp,
                ),
            shape = RoundedCornerShape(10.dp)
        ) {
            ButtonText(
                text = "Create",
                color = Color.White
            )
        }
    }
}