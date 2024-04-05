package com.example.careerboast.view.navigation.routes

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.buildJobDetailRoute
import com.example.careerboast.view.navigation.buildMentorDetailRoute
import com.example.careerboast.view.screens.mentor.MentorToFavoriteScreen
import com.example.careerboast.view.screens.mentor.MentorViewModel
import com.example.careerboast.view.screens.mentor.favoritementor.FavoriteMentorViewModel

@Composable
fun MentorToFavoriteRoute(
    drawerState : DrawerState,
    appState : CareerBoastAppState
) {

    val viewModel : MentorViewModel = hiltViewModel()
    val favoriteViewmodel : FavoriteMentorViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val favoriteUiState by favoriteViewmodel.uiState.collectAsStateWithLifecycle()

    MentorToFavoriteScreen(
        drawerState = drawerState,
        uiState = uiState,
        onEvent = viewModel::obtainEvent,
        favoriteUiState = favoriteUiState,
        onNavigation = { jobId ->
            appState.navigate(buildMentorDetailRoute(jobId))
        }
    )


}