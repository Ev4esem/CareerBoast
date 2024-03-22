package com.example.careerboast.view.screens.interview

sealed interface InterviewListEvent {

    object RefreshData : InterviewListEvent

    data class SelectedInterview(
        val id : Int
    ) : InterviewListEvent

    data class RefreshProductDetail(val id : Int) : InterviewListEvent

    object ClearSelectedSpeciality : InterviewListEvent

}