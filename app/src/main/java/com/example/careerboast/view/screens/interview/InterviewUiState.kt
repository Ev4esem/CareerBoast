package com.example.careerboast.view.screens.interview

import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.model.interviews.StudyMaterial

data class InterviewUiState(
    val selectQuestion : List<Question> = emptyList(),
    val correctQuestionListId : MutableList<String> = mutableListOf(),
    val inCorrectQuestionListId : MutableList<String> = mutableListOf(),
    val currentQuestionIndex : Int = 0,
    val currentAnswerIndex : Int = 0,
    val nextQuestion : Question? = null,
    val error : String? = null,
    val loading : Boolean = false,
    val timerData : TimerData = TimerData(),
    val finishDialogIsVisible : Boolean = false,
    val isTimerFinished : Boolean = false,
    val userAnswers: MutableList<Pair<Question, Boolean>> = mutableListOf()
)
