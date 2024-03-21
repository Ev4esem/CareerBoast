package com.example.careerboast.view.navigation

sealed class Screen(
    val route : String

) {

    object LOGIN_SCREEN : Screen("LoginScreen")
    object INTERVIEWS_SCREEN : Screen("InterviewsScreen")
    object INTERVIEW_SCREEN : Screen("InterviewScreen")
    object MENTORS_SCREEN : Screen("MentorsScreen")
    object INTERSHIPS_SCREEN : Screen("IntershipsScreen")
    object SPECIALITY_SCREEN : Screen("SpecialityScreen")
    object FEEDBACK_SCREEN : Screen("FeedbackScreen")
    object DETAILS_MENTOR_SCREEN : Screen("DetailsMentorScreen")
    object DETAILS_JOB_SCREEN : Screen("FeedbackScreen")
    object FAVORITE_SCREEN : Screen("FavoriteScreen")

}
