package com.example.careerboast.view.screens.interview_list

sealed interface InterviewListEffect {

    data class ShowToast(
        val message : String
    ) : InterviewListEffect

}