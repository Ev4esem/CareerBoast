package com.example.careerboast.data.repositories

import com.example.careerboast.domain.repositories.LogService
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable : Throwable) {
        return Firebase.crashlytics.recordException(throwable)
    }
}