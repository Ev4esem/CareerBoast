package com.example.careerboast.view.navigation.routes

import android.os.Bundle
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.example.careerboast.utils.ANSWER_STATE
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
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
    // todo Не добавил обработки в InterviewViewModel
    ObserveEffect(viewModel.effectFlow) { effect ->
        when (effect) {
            is InterviewEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }

            is InterviewEffect.FinishInterview -> {
                appState.navController.navigate(
                    route = Screen.FEEDBACK_SCREEN.route,
                    args = bundleOf(
                        ANSWER_STATE to effect.result
                    )
                )
            }
        }
    }

    InterviewScreen(
        appState = appState,
        onEvent = viewModel::obtainEvent,
        uiState = uiState
    )
}

fun NavController.navigate(
    route : String,
    args : Bundle,
    navOptions : NavOptions? = null,
    navigatorExtras : Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}