package com.example.careerboast.view.screens.speciality

import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.domain.model.specialities.Speciality

data class SpecialityUiState(
    val specialityList : List<Speciality> = emptyList(),
    val specialityLoading : Boolean = false,
    val interviewsLoading : Boolean = false,
    val error : String? = null,
    val errorInterviews : String? = null,
    var selectSpeciality : List<Interview>? = null
)
