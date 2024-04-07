package com.example.careerboast.view.screens.job

import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.job.GetJobFavoriteListUseCase
import com.example.careerboast.domain.use_cases.job.GetJobsListUseCase
import com.example.careerboast.domain.use_cases.job.SetFavoriteJobUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getJobsListUseCase : GetJobsListUseCase,
    private val getJobFavoriteListUseCase : GetJobFavoriteListUseCase,
    private val setFavoriteJobUseCase : SetFavoriteJobUseCase,
    logService : LogService
) : CareerBoastViewModel(logService), EventHandler<JobEvent>, EffectHandler<JobEffect> {

    private var _jobUiState = MutableStateFlow(JobUiState())
    val jobUiState = _jobUiState.asStateFlow()

    override val effectChannel : Channel<JobEffect> = Channel()

    override fun obtainEvent(event : JobEvent) {
        when(event) {

            JobEvent.RefreshData -> {
                getJobs()
            }

            is JobEvent.ChangeFavorite -> {
                changeFavorite(event.job)
            }
            is JobEvent.ChangeTabs -> {
                changeType(event.tab)
            }

        }
    }

    init {
        getJobs()
    }
    private fun changeType(tab : InternshipJob) {

        if (tab == InternshipJob.Jobs) {
            _jobUiState.update {  currentState ->
                currentState.copy(
                    tab = tab
                )
            }
        } else if(tab == InternshipJob.Favorite) {
            getJobsFavorite()
            _jobUiState.update {  currentState ->
                currentState.copy(
                    tab = tab
                )
            }
        }


    }


    private fun getJobsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {

            getJobFavoriteListUseCase().collectAsResult(
                onSuccess = { jobs ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = jobs,
                            loadingFavorite  = false,
                            errorFavorite = null
                        )
                    }
                },
                onError = { ex, message ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            loadingFavorite = false,
                            errorFavorite = message
                        )
                    }
                    sendEffect(JobEffect.ShowToast(message.toString()))
                },
                onLoading = {
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            loadingFavorite = true,
                            errorFavorite = null
                        )
                    }
                }
            )
        }
    }

    private fun changeFavorite(job: Job) {
        viewModelScope.launch {
            setFavoriteJobUseCase(job).collectAsResult(
                onSuccess = { newValue ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = currentState.favoriteList
                                .map { if (it.id == job.id) it.copy(favorite = !it.favorite) else it },
                        )
                    }
                }
            )
        }
    }

    private fun getJobs() {
        viewModelScope.launch(Dispatchers.IO) {

            getJobsListUseCase().collectAsResult(
                onSuccess = { jobs ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            jobs = jobs,
                            loading  = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            loading = false,
                            error = message
                        )
                    }
                    sendEffect(JobEffect.ShowToast(message.toString()))
                },
                onLoading = {
                    _jobUiState.update { currentState ->
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