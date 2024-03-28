package com.example.careerboast.domain.model.jobs

data class JobDetail(
    val id : String = "",
    val title : String = "",
    val status : String = "",
    val startData : String = "",
    val rating : Float = 0f,
    val review : String = "",
    val location : String = "",
    val nameCompany : String = "",
    val logoCompany : String = "",
    val data : String = "",
    val compensation : String = "",
    val currency : String = "",
    val jobType : String = "",
    val qualifications : String = "",
    val list : List<FeedbackJob> = listOf()
)
