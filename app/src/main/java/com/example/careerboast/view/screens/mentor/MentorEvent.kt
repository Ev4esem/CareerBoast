package com.example.careerboast.view.screens.mentor

import com.example.careerboast.domain.model.mentors.Mentor

sealed interface MentorEvent {

    data object RefreshData : MentorEvent

    data class ChangeFavorite(
        val mentor : Mentor
    ) : MentorEvent

}