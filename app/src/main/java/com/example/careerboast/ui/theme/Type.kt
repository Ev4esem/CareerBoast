package com.example.careerboast.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.careerboast.R

// Set of Material typography styles to start with
val Lexend = FontFamily(
    Font(R.font.lexend, FontWeight.W300),
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_black, FontWeight.Black),
    Font(R.font.lexend_extralight, FontWeight.ExtraLight),
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold),

    )

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W300,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W300,
        fontSize = 10.sp,
    )
)

