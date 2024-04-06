package com.example.careerboast.domain.model.interviews

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

data class StudyMaterial(
    val id: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val url: String = "",
    val task : String = ""
)
