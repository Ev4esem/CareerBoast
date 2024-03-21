package com.example.careerboast.view.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.login.LoginScreen
import com.example.careerboast.view.screens.login.LoginViewModel

@Composable
fun LogInRoute(
    appState : CareerBoastAppState
) {

    val viewModel : LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route,popUp) },
        viewModel = viewModel,
        uiState = uiState)

}