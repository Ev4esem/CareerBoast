package com.example.careerboast.view.screens.job_detail

sealed interface JobDetailEffect {

    data class ShowToast(
        val message : String
    ) : JobDetailEffect

}