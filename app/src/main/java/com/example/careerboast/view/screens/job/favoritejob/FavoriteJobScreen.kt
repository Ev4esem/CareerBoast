package com.example.careerboast.view.screens.job.favoritejob

import androidx.compose.runtime.Composable
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.job.JobEvent
import com.example.careerboast.view.screens.job.JobFavoriteList

// todo
@Composable
fun FavoriteJobScreen(
    onNavigation : (String) -> Unit,
    uiState : FavoriteUiState,
    onEvent : (JobEvent) -> Unit
) {
    if (! uiState.error.isNullOrBlank()) {
        CareerErrorScreen(
            errorText = uiState.error.toString(),
            onClickRetry = {
                onEvent(JobEvent.RefreshData)
            }
        )
    } else if (uiState.loading) {
        CareerLoadingScreen()
    } else {
        JobFavoriteList(
            list = uiState.favoriteList,
            onEvent = onEvent,
            onNavigation = onNavigation
        )

    }
}