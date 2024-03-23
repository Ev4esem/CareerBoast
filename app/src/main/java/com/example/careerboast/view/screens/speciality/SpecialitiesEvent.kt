package com.example.careerboast.view.screens.speciality

sealed interface SpecialitiesEvent {

    object RefreshData : SpecialitiesEvent

    data class SelectedSpeciality(
        val id : String
    ) : SpecialitiesEvent

    data class RefreshInterviewList(val id : String) : SpecialitiesEvent

    object ClearSelectedSpeciality : SpecialitiesEvent

}