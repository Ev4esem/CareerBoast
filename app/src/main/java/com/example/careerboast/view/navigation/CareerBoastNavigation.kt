package com.example.careerboast.view.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.careerboast.view.navigation.routes.InterviewListRoute
import com.example.careerboast.view.navigation.routes.LogInRoute
import com.example.careerboast.view.navigation.routes.SpecialitiesRoute


fun NavGraphBuilder.screens(
    navController : NavController,
    appState : CareerBoastAppState,
) {
    composable(
        route = Screen.LOGIN_SCREEN.route
    ) {

        LogInRoute(
            appState = appState
        )
    }

    composable(route = Screen.SPECIALITY_SCREEN.route) {
        SpecialitiesRoute(navController = appState)
    }

    composable(route = Screen.MENTORS_SCREEN.route) {
    }

    composable(route = Screen.INTERVIEWS_SCREEN.route) {
        InterviewListRoute(appState = appState)
    }

    composable(route = Screen.DETAILS_JOB_SCREEN.route) {
    }

    composable(route = Screen.FAVORITE_SCREEN.route) {
    }

    composable(route = Screen.INTERVIEW_SCREEN.route) {
    }

    composable(route = Screen.INTERSHIPS_SCREEN.route) {
    }

    composable(route = Screen.DETAILS_MENTOR_SCREEN.route) {
    }

}