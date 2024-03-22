package com.example.careerboast.view.screens.interview

sealed interface InterviewListEffect {

    data class ShowToast(
        val message : String
    ) : InterviewListEffect

}