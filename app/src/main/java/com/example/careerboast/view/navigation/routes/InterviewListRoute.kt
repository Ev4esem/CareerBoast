package com.example.careerboast.view.navigation.routes

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.interview.InterviewListEffect
import com.example.careerboast.view.screens.interview.InterviewListScreen
import com.example.careerboast.view.screens.interview.InterviewListViewModel
import com.example.careerboast.view.screens.speciality.SpecialitiesEvent
import com.example.careerboast.view.screens.speciality.SpecialityViewModel

@Composable
fun InterviewListRoute(
    appState : CareerBoastAppState
) {

    val context = LocalContext.current
    val viewModelSpeciality : SpecialityViewModel = hiltViewModel()
    val uiStateSpeciality by viewModelSpeciality.specialitiesUiState.collectAsStateWithLifecycle()
    val viewModelInterviewList : InterviewListViewModel = hiltViewModel()
    val uiStateInterviewList by viewModelInterviewList.interviewListUiState.collectAsStateWithLifecycle()

    ObserveEffect(flow = viewModelInterviewList.effectFlow) { effect ->

        when(effect) {
            is InterviewListEffect.ShowToast -> {
                Toast.makeText(context,effect.message, Toast.LENGTH_LONG).show()
            }
        }

    }

    Log.d("InterviewListId", "${uiStateSpeciality.selectSpeciality} in InterviewListRoute")

    uiStateSpeciality.selectSpeciality?.let {  speciality ->
        Log.d("InterviewListId", "$speciality in InterviewListRoute")

        InterviewListScreen(
            interviewList = speciality.interviews,
            onEvent = viewModelInterviewList::obtainEvent,
            appState = appState,
            error = uiStateInterviewList.errorList,
            loading = uiStateInterviewList.interviewListLoading,
            onClickRetry = {
                viewModelSpeciality.obtainEvent(SpecialitiesEvent.RefreshInterviewList(speciality.id))
            }
        )
    } ?: run {
        Log.d("SpecialityId", "NULL")

    }

}