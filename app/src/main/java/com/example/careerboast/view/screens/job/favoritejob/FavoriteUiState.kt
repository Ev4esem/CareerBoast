package com.example.careerboast.view.screens.job.favoritejob

import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobDetail

data class FavoriteUiState(
    val favoriteList : List<Job> = listOf(),
    val loading : Boolean = false,
    val error : String? = null,
    val selectJob : JobDetail? = null,
)
