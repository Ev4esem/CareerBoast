package com.example.careerboast.view.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.careerboast.R
import com.example.careerboast.ui.theme.CareerBoastTheme
import com.example.careerboast.view.navigation.drawer.AppDrawerContent
import com.example.careerboast.view.navigation.drawer.MenuItem
import com.example.careerboast.view.screens.login.LoginViewModel


@Composable
fun CareerBoastApp(
    modifier : Modifier = Modifier,
    appState : CareerBoastAppState = rememberCareerBoastAppState(),
    viewModel : LoginViewModel = hiltViewModel()
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    CareerBoastTheme {

        Surface(color = MaterialTheme.colorScheme.background) {

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    AppDrawerContent(
                        drawerState = drawerState,
                        menuItem = DrawerParams.drawerButtons,
                        defaultPick = Screen.SPECIALITY_SCREEN
                    ) { onUserPickedOption ->
                        when (onUserPickedOption) {
                            Screen.SPECIALITY_SCREEN -> {
                                appState.navigate(onUserPickedOption.route)
                            }

                            Screen.JOBS_TO_FAVORITE_SCREEN -> {
                                appState.navigate(onUserPickedOption.route)
                            }

                            Screen.MENTORS_SCREEN -> {
                                appState.navigate(onUserPickedOption.route)
                            }

                            else -> {
                                viewModel.onSignOut {
                                    appState.clearAndNavigate(it)
                                }
                            }
                        }


                    }
                }
            ) {
                Scaffold(
                    modifier = modifier
                        .navigationBarsPadding(),
                ) {
                    CareerBoastNavHost(
                        modifier = modifier.padding(it),
                        navController = appState.navController,
                        appState = appState,
                        drawerState = drawerState
                    )
                }
            }
        }
    }
}

object DrawerParams {
    val drawerButtons = arrayListOf(
        MenuItem(
            drawerOption = Screen.SPECIALITY_SCREEN,
            contentDescription = R.string.specialties,
            title = R.string.specialties,
            icon = R.drawable.interview,
        ),
        MenuItem(
            drawerOption = Screen.JOBS_TO_FAVORITE_SCREEN,
            contentDescription = R.string.internships,
            title = R.string.internships,
            icon = R.drawable.interships,
        ),
        MenuItem(
            drawerOption = Screen.MENTORS_SCREEN,
            contentDescription = R.string.mentors,
            title = R.string.mentors,
            icon = R.drawable.mentors,
        ),
        MenuItem(
            drawerOption = Screen.LOGIN_SCREEN,
            contentDescription = R.string.log_out,
            title = R.string.log_out,
            icon = R.drawable.log_out,
        )
    )
}