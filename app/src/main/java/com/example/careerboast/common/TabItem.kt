package com.example.careerboast.common

import androidx.compose.ui.graphics.Color
import com.example.careerboast.view.screens.job.Internship

data class TabItem(
    val title : Int,
    val selected : Color,
    val unSelected : Color,
    val textSelected : Color,
    val textUnSelected : Color,
    val screen : Internship
)
