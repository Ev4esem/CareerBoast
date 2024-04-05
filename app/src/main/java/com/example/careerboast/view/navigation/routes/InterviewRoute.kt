package com.example.careerboast.view.navigation.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.utils.SPECIALITY_ID
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.navigation.buildFeedbackRoute
import com.example.careerboast.view.screens.interview.InterviewEffect
import com.example.careerboast.view.screens.interview.InterviewScreen
import com.example.careerboast.view.screens.interview.InterviewViewModel

@Composable
fun InterviewRoute(
    appState : CareerBoastAppState
) {

    val context = LocalContext.current
    val viewModel : InterviewViewModel = hiltViewModel()
    val uiState by viewModel.interviewUiState.collectAsStateWithLifecycle()
    val answerState by viewModel.answerState.collectAsStateWithLifecycle()
    // todo Не добавил обработки в InterviewViewModel
    ObserveEffect(viewModel.effectFlow) { effect ->
        when (effect) {
            is InterviewEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    InterviewScreen(
        appState = appState,
        onEvent = viewModel::obtainEvent,
        uiState = uiState,
        answerResult = answerState,
        onNavigation = { studyList ->
            //TODO Нужно очистить текущий экран из стека и перейти к другому экрану
            appState.navigateAndPopUp(
                route = buildFeedbackRoute(
                    answerResult = studyList
                ),
                popUp = Screen.INTERVIEW_SCREEN.route + SPECIALITY_ID
            )
        }
    )
}