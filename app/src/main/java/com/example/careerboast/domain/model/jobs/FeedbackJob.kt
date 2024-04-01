package com.example.careerboast.domain.model.jobs

import kotlinx.serialization.Serializable

@Serializable
data class FeedbackJob(
    val description : String = "",
    val feedback : Int = 0,
    val imagePerson : String = "",
    val name : String = ""
)
