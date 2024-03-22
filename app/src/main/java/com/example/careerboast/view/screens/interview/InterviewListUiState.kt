package com.example.careerboast.view.screens.interview

import com.example.careerboast.domain.model.interviews.Interview

data class InterviewListUiState(
    var interviewList : List<Interview> = emptyList(),
    val interviewListLoading : Boolean = false,
    val interviewLoading : Boolean = false,
    val errorList : String? = null,
    val errorInterview : String? = null,
    val selectInterview : Interview? = null
)
