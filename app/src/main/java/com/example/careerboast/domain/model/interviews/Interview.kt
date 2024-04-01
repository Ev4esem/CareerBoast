package com.example.careerboast.domain.model.interviews

import com.google.errorprone.annotations.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Interview(
    val id : String = "",
    val title : String = "",
    val nameCompany : String = "",
    val level : String = "",
    val numberTime : Int = 0,
    val time : String = "",
    val logoCompany : String = "",
    val totalTime : String = ""
)
