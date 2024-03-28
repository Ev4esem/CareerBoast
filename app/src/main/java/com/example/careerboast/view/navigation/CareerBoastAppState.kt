package com.example.careerboast.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberCareerBoastAppState(
    navController : NavHostController = rememberNavController()
) : CareerBoastAppState {
    return remember(navController) {
        CareerBoastAppState(navController)
    }
}

@Stable
class CareerBoastAppState(
    val navController : NavHostController,
) {


    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route : String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateBasic(route : String) {
        navController.navigate(route)
    }

    fun navigateAndPopUp(route : String, popUp : String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route : String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }



}