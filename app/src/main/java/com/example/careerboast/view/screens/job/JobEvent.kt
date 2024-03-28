package com.example.careerboast.view.screens.job

import com.example.careerboast.domain.model.jobs.Job

sealed interface JobEvent {

    data object RefreshData : JobEvent

    data class ChangeFavorite(
        val job : Job
    ) : JobEvent

    data class SelectedJob(
        val id : String
    ) : JobEvent


}