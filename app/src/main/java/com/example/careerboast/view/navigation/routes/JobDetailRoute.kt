package com.example.careerboast.view.navigation.routes

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.careerboast.utils.ObserveEffect
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.screens.job_detail.JobDetailEffect
import com.example.careerboast.view.screens.job_detail.JobDetailScreen
import com.example.careerboast.view.screens.job_detail.JobDetailViewModel

@Composable
fun JobDetailRoute(
    appState : CareerBoastAppState
) {

    val context = LocalContext.current
    val viewModel : JobDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // todo Не добавил обработки в JobDetailViewModel
    ObserveEffect(viewModel.effectFlow) { effect ->
        when (effect) {
            is JobDetailEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    Log.d("JobDetail", "${uiState.jobDetail}")

    uiState.jobDetail?.let {  jobDetail ->
        JobDetailScreen(
            title = jobDetail.title,
            imageUrl = jobDetail.logoCompany,
            nameCompany = jobDetail.nameCompany,
            status = jobDetail.status,
            data = jobDetail.data,
            ratingCompany = jobDetail.rating,
            location = jobDetail.location,
            currency = jobDetail.currency,
            compensation = jobDetail.compensation,
            url = jobDetail.url,
            review = jobDetail.review,
            jobType = jobDetail.jobType,
            qualification = jobDetail.qualifications,
            startDate = jobDetail.startData,
            list = uiState.feedbackList,
            appState = appState
        )
    }



}