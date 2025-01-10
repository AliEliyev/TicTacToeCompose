package com.aecoding.tictactoecompose.presentation.utils

typealias GameBoard = List<List<Char>>

fun GameBoard.check(): Boolean {
    if (
        this[0][0] == this[1][1] &&
        this[1][1] == this[2][2] &&
        this[0][0] != ' '
    ) {
        return true
    } else if (
        this[0][2] == this[1][1] &&
        this[1][1] == this[2][0] &&
        this[0][2] != ' '
    ) {
        return true
    }
    for (i in 0..2) {
        if (this[i][0] == this[i][1] &&
            this[i][1] == this[i][2] &&
            this[i][0] != ' '
        ) {
            return true
        } else if (this[0][i] == this[1][i] &&
            this[1][i] == this[2][i] &&
            this[0][i] != ' '
        ) {
            return true
        }
    }
    return false
}

fun GameBoard.isBoardFull(): Boolean {
    this.forEach { it ->
        it.forEach {
            if (it == ' ') {
                return false
            }
        }
    }
    return true
}