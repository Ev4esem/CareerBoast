package com.example.careerboast.view.screens.mentor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.careerboast.R
import com.example.careerboast.common.TabItem
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.Grey
import com.example.careerboast.ui.theme.LightGreyBackground
import com.example.careerboast.ui.theme.White
import com.example.careerboast.view.navigation.drawer.AppBar
import com.example.careerboast.view.screens.mentor.favoritementor.FavoriteMentorUiState


@Composable
fun MentorToFavoriteScreen(
    drawerState : DrawerState,
    uiState : MentorUiState,
    favoriteUiState : FavoriteMentorUiState,
    onNavigation : (String) -> Unit,
    onEvent : (MentorEvent) -> Unit
) {
    val tabItems = listOf(
        TabItem(
            title = R.string.jobs,
            selected = White,
            unSelected = LightGreyBackground,
            textSelected = Black,
            textUnSelected = Grey,
        ),
        TabItem(
            title = R.string.favorite,
            selected = White,
            unSelected = LightGreyBackground,
            textSelected = Black,
            textUnSelected = Grey,
        )
    )

    Scaffold(
        topBar = {
            AppBar(drawerState = drawerState)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.internships_and_jobs),
                style = MaterialTheme.typography.titleLarge,
                color = Black,
                modifier = Modifier.basisPadding()
            )

            TabLayoutJob(
                tabItems = tabItems,
                uiState = uiState,
                onEvent = onEvent,
                favoriteUiState = favoriteUiState,
                onNavigation = onNavigation
            )

        }
    }


}