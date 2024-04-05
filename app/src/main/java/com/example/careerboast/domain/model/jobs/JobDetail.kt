package com.example.careerboast.domain.model.jobs

data class JobDetail(
    val id : String = "",
    val url : String = "",
    val title : String = "",
    val status : String = "",
    val nameCompany : String = "",
    val logoCompany : String = "",
    val data : String = "",
    val internships_detail : List<InternshipsDetail> = emptyList(),
    val info : List<Info> = emptyList(),
    val feedback_job : List<FeedbackJob> = emptyList()
)
