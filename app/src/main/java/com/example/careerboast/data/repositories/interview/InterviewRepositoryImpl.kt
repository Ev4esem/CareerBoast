package com.example.careerboast.data.repositories.interview

import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.repositories.interview.InterviewRepository
import com.example.careerboast.domain.repositories.interview.TimerListener

class InterviewRepositoryImpl() : InterviewRepository {



    override suspend fun getNextQuestions(currentQuestionId : Int) : Question {
        return Question(1,"", listOf(),0, listOf())
    }

    override fun startTimer(duration : Long, tickInterval : Long, listener : TimerListener) {
    }

    override fun stopTimer() {
    }

    override suspend fun submitAnswer() {
    }

}