package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.YellowShadowColor

@Composable
fun GameBox(
    text: Char,
    onClick: () -> Unit
) {
    val color = remember { mutableStateOf(Color.White) }
    when (text) {
        'X' -> {color.value = BlueShadowColor}
        'O' -> {color.value = YellowShadowColor}
        else -> {color.value = Color.White}
    }
    Box(
        modifier = Modifier
            .padding(5.dp)
            .size(100.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(4.dp),
                ambientColor = color.value,
                spotColor = color.value
            )
            .clickable {
               onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.toString(),
            fontSize = 80.sp,
            lineHeight = 100.32.sp,
            style = TextStyle(
                fontFamily = Orbitron,
                shadow = Shadow(
                    color = color.value,
                    offset = Offset(-3f, -3f),
                    blurRadius = 25f
                )
            ),
            color = Color.White
        )
    }
}
