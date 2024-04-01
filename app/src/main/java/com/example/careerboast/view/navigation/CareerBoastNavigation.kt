package com.example.careerboast.view.navigation

import androidx.compose.material3.DrawerState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.careerboast.domain.model.interviews.StudyMaterial
import com.example.careerboast.utils.ARTICLE_URL
import com.example.careerboast.utils.CORRECT_ANSWER
import com.example.careerboast.utils.INCORRECT_ANSWER
import com.example.careerboast.utils.INTERVIEW_ID
import com.example.careerboast.utils.JOB_ID
import com.example.careerboast.utils.SPECIALITY_ID
import com.example.careerboast.utils.STUDY_LIST
import com.example.careerboast.utils.TIME_TOTAL
import com.example.careerboast.view.navigation.routes.FeedbackRoute
import com.example.careerboast.view.navigation.routes.InterviewListRoute
import com.example.careerboast.view.navigation.routes.InterviewRoute
import com.example.careerboast.view.navigation.routes.JobDetailRoute
import com.example.careerboast.view.navigation.routes.JobToFavoriteRoute
import com.example.careerboast.view.navigation.routes.LogInRoute
import com.example.careerboast.view.navigation.routes.SpecialitiesRoute
import com.example.careerboast.view.screens.feedback.WebViewContainer
import com.google.gson.Gson


fun buildInterviewListRoute(argument: String) = "${Screen.INTERVIEWS_SCREEN.route}/$argument"

fun buildInterviewRoute(interviewId : String, time : String) = "${Screen.INTERVIEW_SCREEN.route}/$interviewId/$time"

fun buildJobDetailRoute(argument: String) = "${Screen.DETAILS_JOB_SCREEN.route}/$argument"

fun buildFeedbackRoute(
    correctAnswer : Int,
    inCorrectAnswer : Int,
    studyMaterials : List<String>?
) : String {
    return "${Screen.FEEDBACK_SCREEN.route}/$correctAnswer/$inCorrectAnswer/$studyMaterials"
}

fun NavGraphBuilder.screens(
    navController : NavController,
    appState : CareerBoastAppState,
    drawerState : DrawerState
) {
    composable(
        route = Screen.LOGIN_SCREEN.route
    ) {

        LogInRoute(
            appState = appState
        )
    }

    composable(route = Screen.SPECIALITY_SCREEN.route) {
        SpecialitiesRoute(navController = navController, drawerState)
    }

    composable(route = Screen.MENTORS_SCREEN.route) {
    }

    composable(
        route = Screen.INTERVIEWS_SCREEN.route + "/{$SPECIALITY_ID}",
    ) {

        InterviewListRoute(appState = appState)

    }

    composable(route = Screen.INTERVIEW_SCREEN.route + "/{$INTERVIEW_ID}" + "/{$TIME_TOTAL}") {

        InterviewRoute(appState = appState)

    }
    composable(
        route = Screen.WEB_VIEW_SCREEN.route + "/{$ARTICLE_URL}",
        arguments = listOf(navArgument(ARTICLE_URL) { type = NavType.StringType })
        ) { backStackEntry ->

        val articleUrl = backStackEntry.arguments?.getString(ARTICLE_URL) ?: ""

        WebViewContainer(url = articleUrl)

    }
    composable(
        route = Screen.FEEDBACK_SCREEN.route + "/{$CORRECT_ANSWER}" + "/{$INCORRECT_ANSWER}" + "/{$STUDY_LIST}",
        arguments = listOf(
            navArgument(STUDY_LIST) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) {
        FeedbackRoute(appState = appState)
    }

    composable(route = Screen.DETAILS_JOB_SCREEN.route + "/{$JOB_ID}") {
        JobDetailRoute(appState = appState)
    }

    composable(route = Screen.JOBS_TO_FAVORITE_SCREEN.route) {

        JobToFavoriteRoute(drawerState = drawerState, appState)
    }

    composable(route = Screen.DETAILS_MENTOR_SCREEN.route) {
    }



}
