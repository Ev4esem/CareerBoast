package com.example.careerboast.view.screens.job

sealed interface JobEffect {

    data class ShowToast(
        val message : String
    ) : JobEffect

}