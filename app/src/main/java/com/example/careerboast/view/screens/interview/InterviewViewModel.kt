package com.example.careerboast.view.screens.interview

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.model.interviews.AnswerResult
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.interview.GetInterviewByIdUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.EventHandler
import com.example.careerboast.utils.INTERVIEW_ID
import com.example.careerboast.utils.TIME_TOTAL
import com.example.careerboast.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(
    private val getInterviewByIdUseCase : GetInterviewByIdUseCase,
    savedStateHandle : SavedStateHandle,
    logService : LogService
) : CareerBoastViewModel(logService), EffectHandler<InterviewEffect>, EventHandler<InterviewEvent> {

    private var countDownTimer : CountDownTimer? = null
    private val interviewId : String = checkNotNull(savedStateHandle[INTERVIEW_ID])
    private val timeTotal : String = checkNotNull(savedStateHandle[TIME_TOTAL])

    override val effectChannel : Channel<InterviewEffect> = Channel()


    private val _answerState = MutableStateFlow<List<AnswerResult>>(emptyList())
    val answerState = _answerState.asStateFlow()


    private val _interviewUiState = MutableStateFlow(InterviewUiState())
    val interviewUiState = _interviewUiState.asStateFlow()

    init {

        val firestoreTimeString = timeTotal
        val timeParts = firestoreTimeString.split(":")
        val minutes = timeParts[0].toLong()
        val seconds = timeParts[1].toLong()

        val initialTimeInMillis =
            TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toSeconds(seconds)
        Log.d("InterviewId", interviewId)
        getQuestions(interviewId)

        startTimer(initialTimeInMillis)
    }

    override fun obtainEvent(event : InterviewEvent) {
        when (event) {
            is InterviewEvent.SelectAnswer -> {
                answerQuestion(event.questionId, event.selectedAnswerIndex)
            }

            InterviewEvent.RefreshData -> {
                getQuestions(interviewId)
            }

            InterviewEvent.SubmitAnswer -> {
                nextQuestion()
            }

            InterviewEvent.FinishedInterview -> {
                processInterviewResults()
            }

            is InterviewEvent.ChangeFinishDialogState -> {
                _interviewUiState.update { currentState ->
                    currentState.copy(
                        finishDialogIsVisible = event.isVisible
                    )
                }
            }
        }
    }

    private fun getQuestions(interviewId : String) {

        viewModelScope.launch {

            getInterviewByIdUseCase(interviewId).collectAsResult(
                onSuccess = { questions ->
                    _interviewUiState.update { currentState ->
                        currentState.copy(
                            selectQuestion = questions,
                            loading = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->

                    _interviewUiState.update { currentState ->
                        currentState.copy(
                            loading = false,
                            error = message
                        )

                    }

                },
                onLoading = {

                    _interviewUiState.update { currentState ->
                        currentState.copy(
                            loading = true,
                            error = null
                        )
                    }

                }
            )

        }

    }


    private fun startTimer(timeInMillis : Long) {

        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished : Long) {

                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

                onTickTimer(minutes, second)

            }

            override fun onFinish() {

                onFinishTimer()

            }

        }.start()
    }

    fun onTickTimer(minutes : Long, second : Long) {
        _interviewUiState.update { currentState ->
            currentState.copy(
                timerData = TimerData(
                    minutes = minutes,
                    seconds = second
                )
            )
        }
    }

    fun onFinishTimer() {
        _interviewUiState.update { currentState ->

            currentState.copy(
                isTimerFinished = true,
            )
        }
    }

    private fun answerQuestion(questionId : String, selectedAnswerId : Int?) {
        viewModelScope.launch {

            val answerResultList = _interviewUiState.value.userAnswers

            answerResultList.add(Pair(questionId, selectedAnswerId))
        }
    }

    private fun processInterviewResults() {
        val answerResultList = _interviewUiState.value.userAnswers

        val answerResult = answerResultList.toAnswerResult()
        _answerState.value = answerResult
    }

    private fun List<Pair<String, Int?>>.toAnswerResult() : List<AnswerResult> {
        return map { (questionId, selectedAnswerId) ->
            val question = _interviewUiState.value.selectQuestion.find { it.id == questionId }
            val answerState = when {
                selectedAnswerId == null -> AnswerResult.AnswerState.NoAnswer
                selectedAnswerId == question?.correctAnswerId -> AnswerResult.AnswerState.Correct
                else -> AnswerResult.AnswerState.Wrong
            }
            AnswerResult(id = questionId, answerState = answerState)
        }
    }

    private fun nextQuestion() {
        viewModelScope.launch {
            val newIndex =
                (_interviewUiState.value.currentQuestionIndex + 1).coerceAtMost(_interviewUiState.value.selectQuestion.size - 1)
            val hasNextQuestion =
                _interviewUiState.value.currentQuestionIndex + 1 < _interviewUiState.value.selectQuestion.size
            val nextQuestion =
                if (hasNextQuestion) _interviewUiState.value.selectQuestion[newIndex] else null

            _interviewUiState.update { currentState ->
                currentState.copy(
                    currentQuestionIndex = newIndex,
                    finishDialogIsVisible = ! hasNextQuestion,
                    nextQuestion = nextQuestion
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}

data class TimerData(
    val minutes : Long = 0,
    val seconds : Long = 0
)
