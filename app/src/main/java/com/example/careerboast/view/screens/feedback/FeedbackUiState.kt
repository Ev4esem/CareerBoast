package com.example.careerboast.view.screens.feedback

import com.example.careerboast.domain.model.interviews.StudyMaterial

data class FeedbackUiState(
    val studyList : List<StudyMaterial> = listOf(),
    val selectStudy : StudyMaterial? = null,
    val correctAnswer : Int = 0,
    val incorrectAnswer : Int = 0,
    val error : String? = null,
    val loading : Boolean = false
)
