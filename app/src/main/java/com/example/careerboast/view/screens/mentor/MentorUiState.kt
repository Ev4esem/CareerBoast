package com.example.careerboast.view.screens.mentor

import com.example.careerboast.domain.model.mentors.Mentor

data class MentorUiState(
    val mentors : List<Mentor> = listOf(),
    val loading : Boolean = false,
    val error : String? = null,
)
