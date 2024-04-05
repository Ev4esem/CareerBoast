package com.example.careerboast.view.screens.mentor_detail

import com.example.careerboast.domain.model.mentors.MentorDetail

data class MentorDetailUiState(
    val mentorDetail : MentorDetail = MentorDetail(),
    val error : String? = null,
    val loading : Boolean = false
)
