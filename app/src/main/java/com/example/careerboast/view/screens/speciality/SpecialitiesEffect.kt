package com.example.careerboast.view.screens.speciality

sealed interface SpecialitiesEffect {

    data class ShowToast(
        val message : String
    ) : SpecialitiesEffect

}