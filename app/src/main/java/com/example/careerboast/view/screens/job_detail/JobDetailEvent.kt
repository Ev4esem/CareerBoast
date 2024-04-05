package com.example.careerboast.view.screens.job_detail

sealed interface JobDetailEvent {

    data object RefreshData : JobDetailEvent


}