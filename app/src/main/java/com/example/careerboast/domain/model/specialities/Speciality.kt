package com.example.careerboast.domain.model.specialities

import com.example.careerboast.domain.model.interviews.Interview
import com.google.errorprone.annotations.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Speciality(
    val id : String = "",
    val imageUrl : String = "",
    val title : String = "",
    val interviews : List<Interview> = listOf()
)
