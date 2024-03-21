package com.example.careerboast.view.navigation.routes

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.screens.speciality.SpecialitiesEffect
import com.example.careerboast.view.screens.speciality.SpecialitiesEvent
import com.example.careerboast.view.screens.speciality.SpecialitiesScreen
import com.example.careerboast.view.screens.speciality.SpecialityViewModel

@Composable
fun SpecialitiesRoute(
    navController : NavController,
) {

    val context = LocalContext.current
    val viewModel : SpecialityViewModel = hiltViewModel()
    val uiState by viewModel.specialitiesUiState.collectAsStateWithLifecycle()

    ObserveEffect(viewModel.effectFlow) { effect ->
        when(effect) {
            is SpecialitiesEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    BackHandler(
        uiState.selectSpeciality != null
    ) {
        viewModel.obtainEvent(
            SpecialitiesEvent.ClearSelectedSpeciality
        )
    }

    SpecialitiesScreen(
        uiState = uiState,
        onEvent = viewModel::obtainEvent,
        navController = navController)

    uiState.selectSpeciality?.let { selectedSpeciality ->

    }

}