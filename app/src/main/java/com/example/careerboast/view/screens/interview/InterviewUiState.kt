package com.example.careerboast.view.screens.interview

import com.example.careerboast.domain.model.interviews.AnswerResult
import com.example.careerboast.domain.model.interviews.Question

data class InterviewUiState(
    val selectQuestion : List<Question> = emptyList(),
    val answerResult : List<AnswerResult> = emptyList(),
    val currentQuestionIndex : Int = 0,
    val currentAnswerIndex : Int = 0,
    val nextQuestion : Question? = null,
    val error : String? = null,
    val loading : Boolean = false,
    val timerData : TimerData = TimerData(),
    val finishDialogIsVisible : Boolean = false,
    val isTimerFinished : Boolean = false,
    val userAnswers: MutableList<Pair<String, Int?>> = mutableListOf()
)
