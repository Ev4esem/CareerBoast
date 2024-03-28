package com.example.careerboast.view.screens.speciality

import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.interview_list.GetSpecialityByIdUseCase
import com.example.careerboast.domain.use_cases.speciality.GetSpecialityListUseCase
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
class SpecialityViewModel @Inject constructor (
    private val getSpecialityListUseCase : GetSpecialityListUseCase,
    logService : LogService
) : CareerBoastViewModel(logService), EffectHandler<SpecialitiesEffect>, EventHandler<SpecialitiesEvent> {

    private var _specialitiesUiState = MutableStateFlow(SpecialityUiState())
    val specialitiesUiState  = _specialitiesUiState.asStateFlow()
    override val effectChannel : Channel<SpecialitiesEffect> = Channel()

    override fun obtainEvent(event : SpecialitiesEvent) {
        when(event) {
            SpecialitiesEvent.RefreshData -> {
                getSpecialities()
            }
        }
    }

    init {
        getSpecialities()
    }

    private fun getSpecialities() {
        viewModelScope.launch(Dispatchers.IO) {

            getSpecialityListUseCase().collectAsResult(
                onSuccess = { specialities ->
                    _specialitiesUiState.update { currentState ->
                        currentState.copy(
                            specialityList = specialities,
                            specialityLoading = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->
                    _specialitiesUiState.update { currentState ->
                        currentState.copy(
                            specialityLoading = false,
                            error = message
                        )
                    }
                },
                onLoading = {
                    _specialitiesUiState.update { currentState ->
                        currentState.copy(
                            specialityLoading = true,
                            error = null
                        )
                    }
                }
            )
        }
    }

    private fun clearSelectedSpeciality() {
        _specialitiesUiState.update { currentState ->

            currentState.copy(
                interviewsLoading = false,
                errorInterviews = null,
                selectSpeciality = listOf()
            )

        }
    }




}