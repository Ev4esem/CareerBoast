package com.example.careerboast.view.screens.job

import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobDetail

data class JobUiState(
    val jobs : List<Job> = listOf(),
    val loading : Boolean = false,
    val error : String? = null,
)
