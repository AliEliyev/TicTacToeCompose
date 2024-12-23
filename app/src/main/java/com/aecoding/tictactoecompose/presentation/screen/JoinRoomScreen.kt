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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aecoding.tictactoecompose.presentation.utils.shadow
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.MainBg
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.PlaceholderColor
import com.aecoding.tictactoecompose.ui.theme.TicTacToeComposeTheme

@Composable
fun JoinRoomScreen() {

    val text = remember { mutableStateOf("") }

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
            Text(
                text = "Join Room",
                style = TextStyle(
                    shadow = Shadow(
                        color = BlueShadowColor,
                        offset = Offset(3f, 3f),
                        blurRadius = 25f
                    )
                ),
                fontFamily = Orbitron,
                fontWeight = FontWeight.W400,
                fontSize = 70.sp,
                lineHeight = 87.78.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                text = "Join Room",
                style = TextStyle(
                    shadow = Shadow(
                        color = BlueShadowColor,
                        offset = Offset(-3f, -3f),
                        blurRadius = 25f
                    )
                ),
                fontFamily = Orbitron,
                fontWeight = FontWeight.W400,
                fontSize = 70.sp,
                lineHeight = 87.78.sp,
                textAlign = TextAlign.Center,
                color = Color.White
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
            placeholder = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp),
                        text = "Enter your nickname",
                        fontFamily = Orbitron,
                        fontWeight = FontWeight.W400,
                        fontSize = 18.sp,
                        lineHeight = 22.57.sp,
                        color = PlaceholderColor,
                        textAlign = TextAlign.Center
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlueShadowColor,
                unfocusedBorderColor = BlueShadowColor.copy(alpha = 0.5f),
            )
        )

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
            placeholder = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp),
                        text = "Enter Room Id",
                        fontFamily = Orbitron,
                        fontWeight = FontWeight.W400,
                        fontSize = 18.sp,
                        lineHeight = 22.57.sp,
                        color = PlaceholderColor,
                        textAlign = TextAlign.Center
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlueShadowColor,
                unfocusedBorderColor = BlueShadowColor.copy(alpha = 0.5f),
            )
        )

        Button(
            onClick = {},
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(50.dp)
                .shadow(
                    color = BlueShadowColor,
                    borderRadius = 10.dp,
                    blurRadius = 3.dp,
                ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Join",
                fontFamily = Orbitron,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                lineHeight = 22.57.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun CreatePreview() {
    TicTacToeComposeTheme {
        JoinRoomScreen()
    }

}