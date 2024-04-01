package com.example.careerboast.view.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.feedback.FeedbackScreen
import com.example.careerboast.view.screens.feedback.FeedbackViewModel

@Composable
fun FeedbackRoute(
    appState : CareerBoastAppState
) {

    val viewModel : FeedbackViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FeedbackScreen(
        uiState = uiState,
        appState = appState)


}