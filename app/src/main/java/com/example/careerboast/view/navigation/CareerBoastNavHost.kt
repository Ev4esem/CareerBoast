package com.example.careerboast.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun CareerBoastNavHost(
    modifier : Modifier = Modifier,
    navController : NavHostController,
    appState : CareerBoastAppState
) {

    val startDestination = MAIN_ROUTE_PATTERN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        main {
            screens(navController, appState)
        }
    }


}