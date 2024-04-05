package com.example.careerboast.domain.model.interviews

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnswerResult(
    val id : String,
    val answerState : AnswerState
) : Parcelable {
    enum class AnswerState {
        Correct, Wrong , NoAnswer
    }
}


