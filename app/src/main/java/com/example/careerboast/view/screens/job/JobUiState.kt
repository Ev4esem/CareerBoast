package com.example.careerboast.view.screens.job

import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobEntity

data class JobUiState(
    val jobs : List<Job> = listOf(),
    val loading : Boolean = false,
    val error : String? = null,
    val loadingFavorite : Boolean = false,
    val errorFavorite : String? = null,
    val tab: InternshipJob = InternshipJob.Jobs,
    val favoriteList : List<JobEntity> = listOf(),
)
enum class InternshipJob {
    Jobs, Favorite
}