package com.example.careerboast.view.screens.mentor.favoritementor

import com.example.careerboast.domain.model.mentors.MentorEntity

data class FavoriteMentorUiState(
    val favoriteMentors : List<MentorEntity> = emptyList(),
    val loading : Boolean = false,
    val error : String? = null,
)
