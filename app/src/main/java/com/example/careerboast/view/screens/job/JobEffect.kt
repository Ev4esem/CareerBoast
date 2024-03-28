package com.example.careerboast.view.screens.job

import com.example.careerboast.view.screens.login.LoginEffect

sealed interface JobEffect {

    data class ShowToast(
        val message : String
    ) : JobEffect

}