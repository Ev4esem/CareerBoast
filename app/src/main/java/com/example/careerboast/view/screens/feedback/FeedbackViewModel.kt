package com.example.careerboast.view.screens.feedback

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.model.interviews.AnswerResult
import com.example.careerboast.domain.model.interviews.StudyMaterial
import com.example.careerboast.domain.use_cases.interview.GetStudyMaterialByIdUseCase
import com.example.careerboast.utils.ANSWER_STATE
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val getStudyMaterialByIdUseCase: GetStudyMaterialByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), EventHandler<FeedbackEvent> {

    companion object {
        const val TAG = "FeedbackViewModel"
    }

    private val _uiState = MutableStateFlow(FeedbackUiState())
    val uiState = _uiState.asStateFlow()

    private val answerResult: List<AnswerResult> = checkNotNull(savedStateHandle[ANSWER_STATE])


    private val questionIds = answerResult
        .filter { it.answerState == AnswerResult.AnswerState.Wrong }
        .map { it.id }
    private val correctAnswerCount =
        answerResult.count { it.answerState == AnswerResult.AnswerState.Correct }
    private val inCorrectAnswerCount =
        answerResult.count { it.answerState == AnswerResult.AnswerState.Wrong }


    override fun obtainEvent(event: FeedbackEvent) {
        when (event) {
            FeedbackEvent.RefreshData -> {
                getStudyMaterialList(questionIds)
                _uiState.update { currentState ->
                    currentState.copy(
                        correctAnswer = correctAnswerCount,
                        incorrectAnswer = inCorrectAnswerCount
                    )
                }
            }
        }
    }

    init {
        Log.d(TAG, "$answerResult: ")
        getStudyMaterialList(questionIds)

        _uiState.update { currentState ->
            currentState.copy(
                correctAnswer = correctAnswerCount,
                incorrectAnswer = inCorrectAnswerCount
            )
        }
    }

    private fun getStudyMaterialList(questionIds: List<String>?) {
        viewModelScope.launch {
            // TODO: При перевороте экрана список исчезнет
            val studyMaterialList = mutableListOf<StudyMaterial>()
            questionIds?.forEach { questionId ->
                getStudyMaterialByIdUseCase(questionId).collectAsResult(
                    onSuccess = { studyMaterial ->
                        studyMaterial?.let { studyMaterialList.add(it) }
                        _uiState.update { currentState ->
                            currentState.copy(
                                studyList = studyMaterialList,
                                loading = false,
                                error = null
                            )
                        }
                    },
                    onError = { ex, message ->
                        _uiState.update { currentState ->
                            currentState.copy(
                                error = message,
                                loading = false
                            )
                        }
                    },
                    onLoading = {
                        _uiState.update { currentState ->
                            currentState.copy(
                                error = null,
                                loading = true
                            )
                        }
                    }
                )
            }
        }
    }


}