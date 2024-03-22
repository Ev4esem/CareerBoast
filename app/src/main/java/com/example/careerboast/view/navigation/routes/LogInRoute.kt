package com.example.careerboast.view.navigation.routes

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.login.LoginEffect
import com.example.careerboast.view.screens.login.LoginScreen
import com.example.careerboast.view.screens.login.LoginViewModel

@Composable
fun LogInRoute(
    appState : CareerBoastAppState
) {

    val context = LocalContext.current
    val viewModel : LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveEffect(viewModel.effectFlow) { effect ->
        when(effect) {
            is LoginEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    LoginScreen(
        openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route,popUp) },
        viewModel = viewModel,
        uiState = uiState)

}