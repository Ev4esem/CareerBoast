package com.example.careerboast.domain.model.jobs

import com.google.errorprone.annotations.Keep
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class Job(
    val id : String = "",
    val logoCompany : String = "",
    val status : String = "",
    val subTitle : String = "",
    val title : String = "",
    var favorite : Boolean = false
)
