package com.example.careerboast.common.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.careerboast.R
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.Grey

@Composable
fun RatingBar(rating : Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            val isFilled = index < rating
            Star(isFilled)
        }
    }
}

@Composable
fun Star(isFilled : Boolean) {
    val starColor = if (isFilled) Blue else Grey
    Icon(
        painter = if (isFilled) {
            painterResource(id = R.drawable.star_fill_feedback)
        } else {
            painterResource(id = R.drawable.star_feedback)
        },
        contentDescription = null,
        tint = starColor
    )
}