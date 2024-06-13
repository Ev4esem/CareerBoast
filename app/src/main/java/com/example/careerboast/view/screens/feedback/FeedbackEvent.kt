package com.example.careerboast.view.screens.feedback

sealed interface FeedbackEvent {
    data object RefreshData : FeedbackEvent

}