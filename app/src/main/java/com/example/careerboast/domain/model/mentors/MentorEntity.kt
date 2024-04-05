package com.example.careerboast.domain.model.mentors

data class MentorEntity(
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


fun MentorEntity.toMentor() = Mentor(
    company = company,
    currency = currency,
    experience = experience,
    feedback = feedback,
    imagePerson = imagePerson,
    name = name,
    position = position,
    price = price,
    surname = surname,
    time = time,
    id = id,
    favorite = favorite
)

fun Mentor.toMentorEntity(favorite : Boolean) = Mentor(
    company = company,
    currency = currency,
    experience = experience,
    feedback = feedback,
    imagePerson = imagePerson,
    name = name,
    position = position,
    price = price,
    surname = surname,
    time = time,
    id = id,
    favorite = favorite
)

