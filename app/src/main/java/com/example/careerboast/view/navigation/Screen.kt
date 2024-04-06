package com.example.careerboast.view.navigation

sealed class Screen(
    val route : String

) {

    object LOGIN_SCREEN : Screen("LoginScreen")
    object INTERVIEWS_SCREEN : Screen("InterviewsScreen")
    object INTERVIEW_SCREEN : Screen("InterviewScreen")
    object MENTORS_SCREEN : Screen("MentorsScreen")
    object SPECIALITY_SCREEN : Screen("SpecialityScreen")
    object FEEDBACK_SCREEN : Screen("FeedbackScreen")
    object DETAILS_MENTOR_SCREEN : Screen("DetailsMentorScreen")
    object DETAILS_JOB_SCREEN : Screen("FeedbackScreen")
    object JOBS_TO_FAVORITE_SCREEN : Screen("jobsToFavoriteScreen")
    object WEB_VIEW_SCREEN : Screen("web_view_screen")
    object SIGN_IN_SCREEN : Screen("sign_in")

}
