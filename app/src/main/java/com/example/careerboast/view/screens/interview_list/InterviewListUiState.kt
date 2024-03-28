package com.example.careerboast.view.screens.interview_list

import com.example.careerboast.domain.model.interviews.Interview

data class InterviewListUiState(
    var interviewList : List<Interview> = emptyList(),
    val interviewListLoading : Boolean = false,
    val errorList : String? = null,
    val selectSpeciality : List<Interview>? = listOf()
)
