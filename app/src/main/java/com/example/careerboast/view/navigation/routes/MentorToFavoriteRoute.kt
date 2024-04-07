package com.example.careerboast.view.navigation.routes

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.buildMentorDetailRoute
import com.example.careerboast.view.screens.mentor.MentorToFavoriteScreen
import com.example.careerboast.view.screens.mentor.MentorViewModel

@Composable
fun MentorToFavoriteRoute(
    drawerState : DrawerState,
    appState : CareerBoastAppState
) {

    val viewModel : MentorViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MentorToFavoriteScreen(
        drawerState = drawerState,
        uiState = uiState,
        onEvent = viewModel::obtainEvent,
        onNavigation = { jobId ->
            appState.navigate(buildMentorDetailRoute(jobId))
        }
    )


}