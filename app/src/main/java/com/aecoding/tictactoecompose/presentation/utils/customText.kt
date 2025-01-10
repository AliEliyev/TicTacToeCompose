package com.aecoding.tictactoecompose.presentation.utils

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.aecoding.tictactoecompose.ui.theme.BlueShadowColor
import com.aecoding.tictactoecompose.ui.theme.Orbitron
import com.aecoding.tictactoecompose.ui.theme.YellowShadowColor

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = Orbitron,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 22.57.sp,
        textAlign = TextAlign.Center,
        color = color
    )
}

@Composable
fun HeaderText(
    text: String,
    textColor: Color,
    shadow: Shadow
) {
    Text(
        text = text,
        style = TextStyle(
            shadow = shadow
        ),
        fontFamily = Orbitron,
        fontWeight = FontWeight.W400,
        fontSize = 70.sp,
        lineHeight = 87.78.sp,
        textAlign = TextAlign.Center,
        color = textColor
    )
}

@Composable
fun DialogText(
    text: String
) {
    Text(
        text = text,
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
}