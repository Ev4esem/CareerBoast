package com.example.careerboast.view.screens.interview

import com.example.careerboast.domain.model.interviews.AnswerResult

sealed interface InterviewEffect {

    data class ShowToast(
        val message : String
    ) : InterviewEffect

    data class FinishInterview(
        val result: List<AnswerResult>,
    ): InterviewEffect
}