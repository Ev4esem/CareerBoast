package com.example.careerboast.view.navigation.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.job.FavoriteJobScreen
import com.example.careerboast.view.screens.job.JobEffect
import com.example.careerboast.view.screens.job.JobViewModel

@Composable
fun FavoriteJobRoute(
    appState : CareerBoastAppState
) {

    val context = LocalContext.current
    val viewModel : JobViewModel = hiltViewModel()
    val uiState by viewModel.jobUiState.collectAsStateWithLifecycle()

    ObserveEffect(viewModel.effectFlow) { effect ->
        when(effect) {
            is JobEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }


    FavoriteJobScreen(
        appState = appState,
        uiState = uiState,
        onEvent = viewModel::obtainEvent
    )

}