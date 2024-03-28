package com.example.careerboast.view.screens.interview_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.interview_list.GetInterviewListUseCase
import com.example.careerboast.domain.use_cases.interview_list.GetSpecialityByIdUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.SPECIALITY_ID
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
class InterviewListViewModel @Inject constructor(
    private val getInterviewListUseCase : GetInterviewListUseCase,
    private val getSpecialityByIdUseCase : GetSpecialityByIdUseCase,
    savedStateHandle : SavedStateHandle,
    logService : LogService
) : CareerBoastViewModel(logService), EffectHandler<InterviewListEffect>, EventHandler<InterviewListEvent> {

    private val specialityId : String = checkNotNull(savedStateHandle[SPECIALITY_ID])

    private var _interviewListUiState = MutableStateFlow(InterviewListUiState())
    val interviewListUiState  = _interviewListUiState.asStateFlow()

    override val effectChannel : Channel<InterviewListEffect> = Channel()

    override fun obtainEvent(event : InterviewListEvent) {
        when(event) {
            InterviewListEvent.RefreshData -> {
                getInterviewList()
            }
            is InterviewListEvent.ClearSelectedSpeciality -> {
                clearSelectedSpeciality()
            }
            is InterviewListEvent.RefreshInterviewList -> {
                getSpecialityById(event.id)
            }
        }
    }

    init {
        getInterviewList()
        getSpecialityById(specialityId)
    }

    private fun getInterviewList() {
        viewModelScope.launch(Dispatchers.IO) {

            getInterviewListUseCase().collectAsResult(
                onSuccess = { specialities ->
                    _interviewListUiState.update { currentState ->
                        currentState.copy(
                            interviewList = specialities,
                            interviewListLoading = false,
                            errorList = null
                        )
                    }
                },
                onError = { ex, message ->
                    _interviewListUiState.update { currentState ->
                        currentState.copy(
                            interviewListLoading = false,
                            errorList = message
                        )
                    }
                },
                onLoading = {
                    _interviewListUiState.update { currentState ->
                        currentState.copy(
                            interviewListLoading = true,
                            errorList = null
                        )
                    }
                }
            )

        }
    }

    private fun clearSelectedSpeciality() {
        _interviewListUiState.update { currentState ->

            currentState.copy(
                interviewListLoading = false,
                errorList = null,
                selectSpeciality = null
            )

        }
    }


    private fun getSpecialityById(id : String) {
        viewModelScope.launch {
            getSpecialityByIdUseCase(
                specialityId = id
            ).collectAsResult(
                onSuccess = { specialityDetail ->
                    Log.d("SelectSpeciality", "$specialityDetail")
                    _interviewListUiState.update { currentState ->
                        currentState.copy(
                            selectSpeciality = specialityDetail,
                            interviewListLoading = false,
                            errorList = null
                        )
                    }
                },
                onError = { ex, message ->
                    _interviewListUiState.update { currentState ->
                        currentState.copy(
                            interviewListLoading = false,
                            errorList = message
                        )
                    }
                },
                onLoading = {
                    _interviewListUiState.update { currentState ->
                        currentState.copy(
                            interviewListLoading = true,
                            errorList = null
                        )
                    }
                }
            )
            sendEffect(InterviewListEffect.ShowToast(message = _interviewListUiState.value.errorList.toString()))
        }
    }


}