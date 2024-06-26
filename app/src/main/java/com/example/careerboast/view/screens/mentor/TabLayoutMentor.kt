package com.example.careerboast.view.screens.mentor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.careerboast.common.TabItemJob
import com.example.careerboast.common.TabItemMentor
import com.example.careerboast.common.ext.spacer
import com.example.careerboast.ui.theme.LightGreyBackground
import com.example.careerboast.view.screens.job.InternshipJob
import com.example.careerboast.view.screens.job.JobEvent
import com.example.careerboast.view.screens.job.JobScreen
import com.example.careerboast.view.screens.job.JobUiState
import com.example.careerboast.view.screens.job.favoritejob.FavoriteJobScreen
import com.example.careerboast.view.screens.mentor.favoritementor.FavoriteMentorScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutMentor(
    uiState : MentorUiState,
    onEvent : (MentorEvent) -> Unit,
    onNavigation : (String) -> Unit
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { TabItemJob.entries.size })
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }

    Column {

        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            containerColor = LightGreyBackground,
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {

            TabItemMentor.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = index == selectedTabIndex.value,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                            onEvent(MentorEvent.ChangeTabs(currentTab.screen))
                        }
                    },

                    text = {
                        Text(
                            text = stringResource(currentTab.title),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                )
            }
        }


        Spacer(modifier = Modifier.spacer())
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                when (TabItemMentor.entries[selectedTabIndex.value].screen) {
                    InternshipMentor.Mentors -> {
                        MentorScreen(
                            uiState = uiState,
                            onEvent = onEvent,
                            onNavigation = onNavigation
                        )
                    }

                    InternshipMentor.Favorite -> {

                        FavoriteMentorScreen(
                            uiState = uiState,
                            onEvent = onEvent,
                            onNavigation = onNavigation
                        )
                    }
                }
            }
        }
    }
}

