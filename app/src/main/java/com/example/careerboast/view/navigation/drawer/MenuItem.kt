package com.example.careerboast.view.navigation.drawer

import com.example.careerboast.R
import com.example.careerboast.utils.SPECIALITIES
import com.example.careerboast.view.navigation.Screen

data class MenuItem(
    val contentDescription : Int,
    val title : Int,
    val icon : Int,
    val route : Screen
)

val items = listOf(
    MenuItem(
        contentDescription = R.string.interview,
        title = R.string.interview,
        icon = R.drawable.interview,
        Screen.SPECIALITY_SCREEN
    ),
    MenuItem(
        contentDescription = R.string.internships,
        title = R.string.internships,
        icon = R.drawable.interships,
        Screen.INTERSHIPS_SCREEN
    ),
    MenuItem(
        contentDescription = R.string.mentors,
        title = R.string.mentors,
        icon = R.drawable.mentors,
        Screen.MENTORS_SCREEN
    ),
    MenuItem(
        contentDescription = R.string.log_out,
        title = R.string.log_out,
        icon = R.drawable.log_out,
        Screen.MENTORS_SCREEN
    ),
)