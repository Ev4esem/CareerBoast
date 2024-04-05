package com.example.careerboast.view.screens.mentor_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.mentor.GetMentorByIdUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.MENTOR_ID
import com.example.careerboast.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorDetailViewModel @Inject constructor(
    private val getMentorByIdUseCase : GetMentorByIdUseCase,
    savedStateHandle : SavedStateHandle,
    logService : LogService
) : CareerBoastViewModel(logService), EventHandler<MentorDetailEvent> {

    private val mentorId : String = checkNotNull(savedStateHandle[MENTOR_ID])

    private val _uiState = MutableStateFlow(MentorDetailUiState())
    val uiState = _uiState.asStateFlow()
    override fun obtainEvent(event : MentorDetailEvent) {
        when(event) {
            MentorDetailEvent.RefreshData -> {
                getJobDetailById(mentorId)
            }
        }
    }


    init {
        getJobDetailById(mentorId)
    }

    private fun getJobDetailById(id : String) {
        viewModelScope.launch {
            getMentorByIdUseCase(
                mentorId = id
            ).collectAsResult(
                onSuccess = { jobDetail ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            mentorDetail = jobDetail,
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