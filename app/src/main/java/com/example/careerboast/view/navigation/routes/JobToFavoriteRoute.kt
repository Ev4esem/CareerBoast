package com.example.careerboast.view.navigation.routes

import android.widget.Toast
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.job.JobEffect
import com.example.careerboast.view.screens.job.JobViewModel
import com.example.careerboast.view.screens.job.Jobs_to_FavoriteJobs

@Composable
fun JobToFavoriteRoute(
    appState : CareerBoastAppState,
    drawerState : DrawerState
) {

    val context = LocalContext.current
    val viewModel : JobViewModel = hiltViewModel()
    val uiState by viewModel.jobUiState.collectAsStateWithLifecycle()

    ObserveEffect(viewModel.effectFlow) { effect ->
        when (effect) {
            is JobEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    Jobs_to_FavoriteJobs(
        appState = appState,
        drawerState = drawerState,
        uiState = uiState,
        onEvent = viewModel::obtainEvent
    )

}