package com.example.careerboast.view.screens.job_detail

import com.example.careerboast.domain.model.jobs.FeedbackJob
import com.example.careerboast.domain.model.jobs.JobDetail

data class JobDetailUiState(
    val jobDetail : JobDetail? = null,
    val feedbackList : List<FeedbackJob> = listOf(),
    val error : String? = null,
    val loading : Boolean = false
)
