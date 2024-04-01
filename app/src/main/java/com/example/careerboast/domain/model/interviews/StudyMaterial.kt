package com.example.careerboast.domain.model.interviews

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class StudyMaterial(
    val id: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val url: String = "",
    val task : String = ""
)
