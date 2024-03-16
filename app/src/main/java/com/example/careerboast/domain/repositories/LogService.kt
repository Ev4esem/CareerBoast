package com.example.careerboast.domain.repositories

interface LogService {

    fun logNonFatalCrash(throwable : Throwable)

}