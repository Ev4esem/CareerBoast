package com.example.careerboast.domain.repositories.interview

import com.example.careerboast.domain.model.interviews.Question

interface InterviewRepository {


   suspend fun getNextQuestions(currentQuestionId : Int) : Question

   fun startTimer(duration: Long, tickInterval: Long, listener: TimerListener)

   fun stopTimer()

   suspend fun submitAnswer()

}