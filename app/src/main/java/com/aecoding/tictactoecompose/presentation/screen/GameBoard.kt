package com.aecoding.tictactoecompose.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun GameBoard(
    board: MutableList<MutableList<Char>>,
    onCellClick: (Int, Int) -> Unit,
) {
    board.forEachIndexed { rowIndex, row ->
        Row {
            row.forEachIndexed { colIndex, text ->
                GameBox(text) {
                    onCellClick(rowIndex, colIndex)
                }
            }
        }
    }
}