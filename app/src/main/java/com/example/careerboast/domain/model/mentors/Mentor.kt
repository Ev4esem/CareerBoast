package com.example.careerboast.domain.model.mentors

data class Mentor(
    val company : String = "",
    val currency : String = "",
    val experience : String = "",
    val feedback : Double = 0.0,
    val imagePerson : String = "",
    val name : String = "",
    val position : String = "",
    val price : Int = 0,
    val surname : String = "",
    val time : String = "",
    val id : String = "",
    val favorite : Boolean = false
)
