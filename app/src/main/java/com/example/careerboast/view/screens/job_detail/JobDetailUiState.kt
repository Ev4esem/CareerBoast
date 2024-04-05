package com.example.careerboast.view.screens.job_detail

import com.example.careerboast.domain.model.jobs.JobDetail

data class JobDetailUiState(
    val jobDetail : JobDetail = JobDetail(),
    val error : String? = null,
    val loading : Boolean = false
)
