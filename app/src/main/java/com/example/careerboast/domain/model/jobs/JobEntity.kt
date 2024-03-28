package com.example.careerboast.domain.model.jobs

data class JobEntity(
    val id : String = "",
    val logoCompany : String = "",
    val status : String = "",
    val subTitle : String = "",
    val title : String = "",
    val favorite : Boolean = false
)
