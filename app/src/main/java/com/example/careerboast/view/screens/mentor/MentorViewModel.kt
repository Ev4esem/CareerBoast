package com.example.careerboast.view.screens.mentor

import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.model.mentors.Mentor
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.mentor.GetMentorsListUseCase
import com.example.careerboast.domain.use_cases.mentor.SetFavoriteMentorUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorViewModel @Inject constructor(
    private val getMentorsListUseCase : GetMentorsListUseCase,
    private val setFavoriteMentorUseCase : SetFavoriteMentorUseCase,
    logService : LogService
) : CareerBoastViewModel(logService), EventHandler<MentorEvent> {

    private val _uiState = MutableStateFlow(MentorUiState())
     val uiState = _uiState.asStateFlow()


    override fun obtainEvent(event : MentorEvent) {

        when(event) {

            MentorEvent.RefreshData -> {
                getMentors()
            }

            is MentorEvent.ChangeFavorite -> {
                changeFavorite(event.mentor)
            }

        }

    }

    init {
        getMentors()
    }

    private fun getMentors() {
        viewModelScope.launch(Dispatchers.IO) {

            getMentorsListUseCase().collectAsResult(
                onSuccess = { jobs ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            mentors = jobs,
                            loading  = false,
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


    private fun changeFavorite(mentor: Mentor) {
        viewModelScope.launch {
            setFavoriteMentorUseCase(mentor).collectAsResult(
                onSuccess = { newValue ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            mentors = currentState.mentors.toMutableList()
                                .map { if (it.id == mentor.id) it.copy(favorite = newValue) else it },
                        )
                    }
                }
            )
        }
    }



}