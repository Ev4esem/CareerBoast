package com.example.careerboast.domain.model.mentors

data class MentorDetail(
    val currency : String = "",
    val description : String = "",
    val feedbacks : List<FeedbackMentor> = emptyList(),
    val id : String = "",
    val imagePerson : String = "",
    val location : String = "",
    val name : String = "",
    val numberTime : Int = 0,
    val position : String = "",
    val year : Int = 0,
    val price : Int = 0,
    val imageUrlPrice : String = "",
    val skills : List<Skills> = emptyList(),
    val socialMedia : List<SocialMedia> = emptyList(),
    val time : String = "",
    val contacts : List<Contact> = emptyList()
)
