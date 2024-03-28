package com.example.careerboast.view.screens.interview_list

sealed interface InterviewListEvent {

    object RefreshData : InterviewListEvent

    data class RefreshInterviewList(val id : String) : InterviewListEvent

    object ClearSelectedSpeciality : InterviewListEvent

}