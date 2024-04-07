package com.example.careerboast.domain.model.jobs


data class Job(
    val id : String = "",
    val logoCompany : String = "",
    val status : String = "",
    val subTitle : String = "",
    val title : String = "",
    var favorite : Boolean = false
)
