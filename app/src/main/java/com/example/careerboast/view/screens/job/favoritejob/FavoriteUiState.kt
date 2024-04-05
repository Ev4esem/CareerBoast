package com.example.careerboast.view.screens.job.favoritejob

import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobDetail
import com.example.careerboast.domain.model.jobs.JobEntity

data class FavoriteUiState(
    val favoriteList : List<JobEntity> = listOf(),
    val loading : Boolean = false,
    val error : String? = null,
)
