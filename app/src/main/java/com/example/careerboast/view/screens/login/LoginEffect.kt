package com.example.careerboast.view.screens.login

sealed interface LoginEffect {

    data class ShowToast(
        val message : String
    ) : LoginEffect

}