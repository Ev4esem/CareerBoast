package com.example.careerboast.view.screens.job_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.job.GetJobByIdUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.JOB_ID
import com.example.careerboast.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(
    private val getJobByIdUseCase: GetJobByIdUseCase,
    savedStateHandle: SavedStateHandle,
    logService: LogService
) : CareerBoastViewModel(logService), EventHandler<JobDetailEvent>, EffectHandler<JobDetailEffect> {

    private val _uiState = MutableStateFlow(JobDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val jobId: String = checkNotNull(savedStateHandle[JOB_ID])

    override val effectChannel: Channel<JobDetailEffect> = Channel()

    override fun obtainEvent(event: JobDetailEvent) {
        when (event) {
            JobDetailEvent.RefreshData -> {
                getJobDetailById(jobId)
            }
        }
    }

    init {
        getJobDetailById(jobId)
    }

    private fun getJobDetailById(id: String) {
        viewModelScope.launch {
            getJobByIdUseCase(
                jobId = id
            ).collectAsResult(
                onSuccess = { jobDetail ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            jobDetail = jobDetail,
                            loading = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            loading = false,
                            error = message
                        )
                    }
                },
                onLoading = {
                    _uiState.update { currentState ->
                        currentState.copy(
                            loading = true,
                            error = null
                        )
                    }
                }
            )
        }
    }
}