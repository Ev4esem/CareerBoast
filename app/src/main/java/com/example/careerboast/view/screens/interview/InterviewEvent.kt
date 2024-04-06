package com.example.careerboast.view.screens.interview

sealed interface InterviewEvent {

    data object RefreshData : InterviewEvent


    data class SelectAnswer(
        val selectedAnswerIndex: Int
    ) : InterviewEvent

    data object FinishedInterview : InterviewEvent

    data class SubmitAnswer(
       val selectedAnswerId : Int?,
       val questionId : String
    ) : InterviewEvent


    data class ChangeFinishDialogState(
        val isVisible: Boolean,
    ): InterviewEvent

}