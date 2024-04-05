package com.example.careerboast.view.navigation.routes

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.job_detail.JobDetailEffect
import com.example.careerboast.view.screens.job_detail.JobDetailScreen
import com.example.careerboast.view.screens.job_detail.JobDetailViewModel

@Composable
fun JobDetailRoute(
    appState : CareerBoastAppState
) {

    val context = LocalContext.current
    val viewModel : JobDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // todo Не добавил обработки в JobDetailViewModel
    ObserveEffect(viewModel.effectFlow) { effect ->
        when (effect) {
            is JobDetailEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    Log.d("JobDetail", "${uiState.jobDetail}")

    JobDetailScreen(
        uiState = uiState,
        appState = appState,
        onEvent = viewModel::obtainEvent
        )


}