package com.example.careerboast.view.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.careerboast.ui.theme.CareerBoastTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareerBoastApp(
    modifier : Modifier = Modifier,
    appState : CareerBoastAppState = rememberCareerBoastAppState()
) {

    CareerBoastTheme {

        Surface(color = MaterialTheme.colorScheme.background) {


            Scaffold(
                modifier = modifier
                    .navigationBarsPadding()
            ) {

               CareerBoastNavHost(
                   modifier = modifier.padding(it),
                   navController = appState.navController,
                   appState = appState
               )

            }

        }

    }

}