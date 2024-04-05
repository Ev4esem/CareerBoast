package com.example.careerboast.view.screens.job.favoritejob

import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobEntity
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.job.GetJobFavoriteListUseCase
import com.example.careerboast.domain.use_cases.job.SetFavoriteJobUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.collectAsResult
import com.example.careerboast.view.screens.job.JobEffect
import com.example.careerboast.view.screens.job.JobEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteJobViewModel @Inject constructor(
    private val setFavoriteJobUseCase : SetFavoriteJobUseCase,
    private val getJobFavoriteListUseCase : GetJobFavoriteListUseCase,
    logService : LogService
): CareerBoastViewModel(logService), EventHandler<JobEvent>, EffectHandler<JobEffect> {

    private var _jobUiState = MutableStateFlow(FavoriteUiState())
    val jobUiState = _jobUiState.asStateFlow()

    override val effectChannel : Channel<JobEffect> = Channel()

    override fun obtainEvent(event : JobEvent) {
        when(event) {

            JobEvent.RefreshData -> {
                getJobsFavorite()
            }
            is JobEvent.ChangeFavorite -> {
                changeFavorite(event.job)
            }

        }
    }

    init {
        getJobsFavorite()
    }


    private fun getJobsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {

            getJobFavoriteListUseCase().collectAsResult(
                onSuccess = { jobs ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = jobs,
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

    private fun changeFavorite(job: Job) {
        viewModelScope.launch {
            setFavoriteJobUseCase(job).collectAsResult(
                onSuccess = { newValue ->
                    _jobUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = currentState.favoriteList.toMutableList()
                                .map { if (it.id == job.id) it.copy(favorite = newValue) else it },
                        )
                    }
                }
            )
        }
    }

}