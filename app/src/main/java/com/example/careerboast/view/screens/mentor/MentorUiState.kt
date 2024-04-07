package com.example.careerboast.view.screens.mentor

import com.example.careerboast.domain.model.mentors.Mentor
import com.example.careerboast.domain.model.mentors.MentorEntity

data class MentorUiState(
    val mentors : List<Mentor> = listOf(),
    val favoriteMentors : List<MentorEntity> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null,
    val loadingFavorite : Boolean = false,
    val errorFavorite : String? = null,
    val tab: InternshipMentor = InternshipMentor.Mentors,
)
enum class InternshipMentor {
    Mentors, Favorite
}