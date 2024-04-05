package com.example.careerboast.view.screens.mentor_detail

import com.example.careerboast.view.screens.job_detail.JobDetailEvent

sealed interface MentorDetailEvent {

    data object RefreshData : MentorDetailEvent


}