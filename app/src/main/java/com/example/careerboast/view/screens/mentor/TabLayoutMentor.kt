package com.example.careerboast.view.screens.mentor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.careerboast.common.TabItem
import com.example.careerboast.common.ext.spacer
import com.example.careerboast.ui.theme.LightGreyBackground
import com.example.careerboast.view.screens.mentor.favoritementor.FavoriteMentorScreen
import com.example.careerboast.view.screens.mentor.favoritementor.FavoriteMentorUiState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutJob(
    tabItems : List<TabItem>,
    uiState : MentorUiState,
    favoriteUiState : FavoriteMentorUiState,
    onEvent : (MentorEvent) -> Unit,
    onNavigation : (String) -> Unit
) {
    Column {

        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }

        val pagerState = rememberPagerState {
            tabItems.size
        }

        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {

            if (! pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = LightGreyBackground,
            indicator = {
                Box(
                    modifier = Modifier
                        .height(0.dp)
                        .width(0.dp)
                )
            },
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {

            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .padding(horizontal = 16.dp)
                        .background(
                            color = if (selectedTabIndex == index) item.selected else item.unSelected,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    text = {
                        Text(
                            text = stringResource(item.title),
                            color = if (selectedTabIndex == index) item.textSelected else item.textUnSelected,
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
                when (index) {
                    0 -> {
                        MentorScreen(
                            uiState = uiState,
                            onEvent = onEvent,
                            onNavigation = onNavigation
                        )
                    }

                    1 -> {
                        FavoriteMentorScreen(
                            uiState = favoriteUiState,
                            onEvent = onEvent,
                            onNavigation = onNavigation
                        )
                    }
                }
            }
        }
    }
}

