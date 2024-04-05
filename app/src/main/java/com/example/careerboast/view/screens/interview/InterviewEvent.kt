package com.example.careerboast.view.screens.interview

sealed interface InterviewEvent {

    data object RefreshData : InterviewEvent


    data class SelectAnswer(
        val questionId: String,
        val selectedAnswerIndex: Int
    ) : InterviewEvent

    data object FinishedInterview : InterviewEvent

    data object SubmitAnswer : InterviewEvent

    data class ChangeFinishDialogState(
        val isVisible: Boolean,
    ): InterviewEvent

}