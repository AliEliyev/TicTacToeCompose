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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aecoding.tictactoecompose.domain.entities.GameEffect
import com.aecoding.tictactoecompose.domain.entities.TypeError
import com.aecoding.tictactoecompose.presentation.utils.ButtonText
import com.aecoding.tictactoecompose.presentation.utils.HeaderText
import com.aecoding.tictactoecompose.presentation.utils.buttonShadow
import com.aecoding.tictactoecompose.presentation.viewmodel.JoinViewModel
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.MainBg
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.PlaceholderColor

@Composable
fun JoinRoomScreen(
    joinViewModel: JoinViewModel = viewModel(),
    onNavigateToGame: (String) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val roomId = remember { mutableStateOf("") }
    //var isEmpty by remember { mutableStateOf(false) }
    val typeErrors = remember { mutableStateListOf<TypeError>() }

    //! ViewModel
    val effect by joinViewModel.gameEffect.collectAsStateWithLifecycle()
    val roomErrors by joinViewModel.roomErrors.collectAsStateWithLifecycle()

    LaunchedEffect(effect) {
        if (effect == GameEffect.NAVIGATE) {
            joinViewModel.joinRoom(roomId.value, name.value)
            onNavigateToGame(roomId.value)
        }
    }


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
                text = "Join Room",
                textColor = Color.White,
                shadow = Shadow(
                    color = BlueShadowColor,
                    offset = Offset(3f, 3f),
                    blurRadius = 25f
                )
            )
            HeaderText(
                text = "Join Room",
                textColor = Color.White,
                shadow = Shadow(
                    color = BlueShadowColor,
                    offset = Offset(-3f, -3f),
                    blurRadius = 25f
                )
            )
        }

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
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
            ),
            singleLine = true
        )

        OutlinedTextField(
            value = roomId.value,
            onValueChange = { if (it.length <= 7) roomId.value = it },
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
                if (typeErrors.isNotEmpty()) {
                    Column {
                        typeErrors.forEach { error ->
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = error.text,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                } else if (roomErrors.isNotEmpty()) {
                    Column {
                        roomErrors.forEach { error ->
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = error.text,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ButtonText(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Enter Room Id",
                        color = PlaceholderColor
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlueShadowColor,
                unfocusedBorderColor = BlueShadowColor.copy(alpha = 0.5f),
            ),
            singleLine = true
        )

        Button(
            onClick = {
                if ((name.value.isEmpty() || roomId.value.isEmpty()) && !typeErrors.contains(
                        TypeError.EMPTY_INPUT
                    )
                ) {
                    typeErrors += TypeError.EMPTY_INPUT
                } else if (!(name.value.isEmpty() || roomId.value.isEmpty()) && typeErrors.contains(
                        TypeError.EMPTY_INPUT
                    )
                ) {
                    typeErrors -= TypeError.EMPTY_INPUT
                }

                if (roomId.value.length < 7 && !typeErrors.contains(TypeError.ID_TOO_SHORT)) {
                    typeErrors += TypeError.ID_TOO_SHORT
                } else if (roomId.value.length == 7 && typeErrors.contains(TypeError.ID_TOO_SHORT)) {
                    typeErrors -= TypeError.ID_TOO_SHORT
                }

                if (!roomId.value.isDigitsOnly() && !typeErrors.contains(TypeError.ID_NOT_NUMERIC)) {
                    typeErrors += TypeError.ID_NOT_NUMERIC
                } else if (roomId.value.isDigitsOnly() && typeErrors.contains(TypeError.ID_NOT_NUMERIC)) {
                    typeErrors -= TypeError.ID_NOT_NUMERIC
                }

                if (typeErrors.isEmpty()) {
                    joinViewModel.idChecker(roomId = roomId.value)
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
                text = "Join",
                color = Color.White
            )
        }
    }
}
