package com.example.careerboast.view.screens.interview_list

sealed interface InterviewListEvent {

    object RefreshData : InterviewListEvent

    object ClearSelectedSpeciality : InterviewListEvent

}