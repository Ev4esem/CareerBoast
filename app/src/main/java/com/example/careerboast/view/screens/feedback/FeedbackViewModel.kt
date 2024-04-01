package com.example.careerboast.view.screens.feedback

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.careerboast.domain.model.interviews.StudyMaterial
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.utils.CORRECT_ANSWER
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.INCORRECT_ANSWER
import com.example.careerboast.utils.STUDY_LIST
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.JsonParseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    savedStateHandle : SavedStateHandle,
    logService : LogService
) : CareerBoastViewModel(logService) {

    private val _uiState = MutableStateFlow(FeedbackUiState())
    val uiState = _uiState.asStateFlow()

    private val correctAnswerCount : Int = /*checkNotNull(savedStateHandle[CORRECT_ANSWER])*/ 0
    private val inCorrectAnswerCount : Int = /*checkNotNull(savedStateHandle[INCORRECT_ANSWER])*/ 0
    private val studyList : String = /*checkNotNull(savedStateHandle[STUDY_LIST])*/  "sd"

    init {

       val studyListMaterial = emptyList<StudyMaterial>()
        _uiState.update { currentState ->
            currentState.copy(
                studyList = studyListMaterial,
                correctAnswer = correctAnswerCount,
                incorrectAnswer = inCorrectAnswerCount
            )
        }
    }

    private fun getStudyMaterialsFromJson(json : String) : List<StudyMaterial> {
        return json.let {
            try {
                val listType = object : TypeToken<List<StudyMaterial>>() {}.type
                Gson().fromJson(json, listType)
            } catch (e : JsonParseException) {
                emptyList()
            }
        }
    }

}