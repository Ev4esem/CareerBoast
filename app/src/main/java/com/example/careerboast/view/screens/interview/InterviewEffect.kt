package com.example.careerboast.view.screens.interview

sealed interface InterviewEffect {

    data class ShowToast(
        val message : String
    ) : InterviewEffect

}