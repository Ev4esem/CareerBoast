package com.example.careerboast.view.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.mentor_detail.MentorDetailScreen
import com.example.careerboast.view.screens.mentor_detail.MentorDetailViewModel

@Composable
fun MentorDetailRoute(
    appState : CareerBoastAppState
) {

    val viewModel : MentorDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MentorDetailScreen(
        appState = appState,
        uiState = uiState,
        onEvent = viewModel::obtainEvent
        )

}