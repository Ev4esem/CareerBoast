package com.example.careerboast.view.screens.speciality

sealed interface SpecialitiesEvent {

    object RefreshData : SpecialitiesEvent

    data class SelectedSpeciality(
        val id : Int
    ) : SpecialitiesEvent

    data class RefreshInterviewList(val id : Int) : SpecialitiesEvent

    object ClearSelectedSpeciality : SpecialitiesEvent

}