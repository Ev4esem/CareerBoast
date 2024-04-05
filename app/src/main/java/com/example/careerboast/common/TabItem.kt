package com.example.careerboast.common

import androidx.compose.ui.graphics.Color

data class TabItem(
    val title : Int,
    val selected : Color,
    val unSelected : Color,
    val textSelected : Color,
    val textUnSelected : Color,
)
