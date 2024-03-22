package com.example.careerboast.domain.repositories.interview

interface TimerListener {

    fun onTimerTick(timeRemaining: Long)

    fun onTimerFinish()

}