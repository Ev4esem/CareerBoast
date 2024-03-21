package com.example.careerboast.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val MAIN_ROUTE_PATTERN = "main_graph"


fun NavGraphBuilder.main(
    nestedGraphs : NavGraphBuilder.() -> Unit
) {
    navigation(
        route = MAIN_ROUTE_PATTERN,
        startDestination = Screen.LOGIN_SCREEN.route
    ) {
        nestedGraphs()
    }
}
