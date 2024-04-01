package com.example.careerboast.view.navigation.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.buildInterviewRoute
import com.example.careerboast.view.screens.interview_list.InterviewListEffect
import com.example.careerboast.view.screens.interview_list.InterviewListScreen
import com.example.careerboast.view.screens.interview_list.InterviewListViewModel

@Composable
fun InterviewListRoute(
    appState : CareerBoastAppState,
) {

    val context = LocalContext.current
    val viewModelInterviewList : InterviewListViewModel = hiltViewModel()
    val uiStateInterviewList by viewModelInterviewList.interviewListUiState.collectAsStateWithLifecycle()

    ObserveEffect(flow = viewModelInterviewList.effectFlow) { effect ->

        when (effect) {
            is InterviewListEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }

    }

    InterviewListScreen(
        onEvent = viewModelInterviewList::obtainEvent,
        appState = appState,
        uiState = uiStateInterviewList,
        onNavigation = { interviewId, time ->
            appState.navigate(buildInterviewRoute(interviewId, time))
        }
    )

}