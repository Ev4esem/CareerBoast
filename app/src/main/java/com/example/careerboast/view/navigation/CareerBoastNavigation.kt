package com.example.careerboast.view.navigation

import androidx.compose.material3.DrawerState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.careerboast.utils.SPECIALITY_ID
import com.example.careerboast.view.navigation.routes.FavoriteJobRoute
import com.example.careerboast.view.navigation.routes.InterviewListRoute
import com.example.careerboast.view.navigation.routes.JobToFavoriteRoute
import com.example.careerboast.view.navigation.routes.LogInRoute
import com.example.careerboast.view.navigation.routes.SpecialitiesRoute
import com.example.careerboast.view.screens.job.FavoriteJobScreen
import com.example.careerboast.view.screens.job.JobScreen


fun buildInterviewListRoute(argument: String) = "${Screen.INTERVIEWS_SCREEN.route}/$argument"

fun NavGraphBuilder.screens(
    navController : NavController,
    appState : CareerBoastAppState,
    drawerState : DrawerState
) {
    composable(
        route = Screen.LOGIN_SCREEN.route
    ) {

        LogInRoute(
            appState = appState
        )
    }

    composable(route = Screen.SPECIALITY_SCREEN.route) {
        SpecialitiesRoute(navController = navController, drawerState)
    }

    composable(route = Screen.MENTORS_SCREEN.route) {
    }

    composable(
        route = Screen.INTERVIEWS_SCREEN.route + "/{$SPECIALITY_ID}",
    ) {

        InterviewListRoute(appState = appState)

    }

    composable(route = Screen.DETAILS_JOB_SCREEN.route) {
    }

    composable(route = Screen.JOBS_TO_FAVORITE_SCREEN.route) {

        JobToFavoriteRoute(appState = appState, drawerState = drawerState)
    }

    composable(route = Screen.DETAILS_MENTOR_SCREEN.route) {
    }



}
