package com.example.careerboast.view.screens.job

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.TabItem
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.common.composable.TabLayout
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.ui.theme.Grey
import com.example.careerboast.ui.theme.LightGreyBackground
import com.example.careerboast.ui.theme.White
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.navigation.drawer.AppBar
import com.example.careerboast.view.screens.speciality.SpecialitiesEvent
import com.example.careerboast.view.screens.speciality.SpecialitiesList

@Composable
fun JobScreen(
    appState : CareerBoastAppState,
    uiState : JobUiState,
    onEvent : (JobEvent) -> Unit
) {

    if (! uiState.error.isNullOrBlank()) {
        CareerErrorScreen(
            errorText = uiState.error.toString(),
            onClickRetry = {
                onEvent(JobEvent.RefreshData)
            }
        )
    } else if (uiState.loading) {
        CareerLoadingScreen()
    } else {
        JobList(
            jobs = uiState.jobs,
            onEvent = onEvent,
            appState = appState
        )

    }


}

@Composable
fun FavoriteJobScreen(
    appState : CareerBoastAppState,
    uiState : JobUiState,
    onEvent : (JobEvent) -> Unit
) {
    if (! uiState.error.isNullOrBlank()) {
        CareerErrorScreen(
            errorText = uiState.error.toString(),
            onClickRetry = {
                onEvent(JobEvent.RefreshData)
            }
        )
    } else if (uiState.loading) {
        CareerLoadingScreen()
    } else {
        JobFavoriteList(
            list = uiState.favoriteList,
            onEvent = onEvent,
            appState = appState
        )

    }
}

@Composable
fun Jobs_to_FavoriteJobs(
    appState : CareerBoastAppState,
    drawerState : DrawerState,
    uiState : JobUiState,
    onEvent : (JobEvent) -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabList = listOf(
        TabItem(
            title = R.string.jobs,
            selected = White,
            unSelected = LightGreyBackground,
            textSelected = Black,
            textUnSelected = Grey,
            screen = Screen.INTERSHIPS_SCREEN.route
        ),
        TabItem(
            title = R.string.favorite,
            selected = White,
            unSelected = LightGreyBackground,
            textSelected = Black,
            textUnSelected = Grey,
            screen = Screen.FAVORITE_JOB_SCREEN.route
        )
    )

    Scaffold(
        topBar = {
            AppBar(drawerState = drawerState)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(id = R.string.internships_and_jobs),
                style = MaterialTheme.typography.titleLarge,
                color = Black,
                modifier = Modifier.padding(top = 8.dp)
            )
            TabLayout(tabItems = tabList, appState = appState)

            when (selectedTabIndex) {
                0 -> {
                    JobScreen(appState = appState, uiState = uiState, onEvent = onEvent)
                }

                1 -> {
                    FavoriteJobScreen(appState = appState, uiState = uiState, onEvent = onEvent)
                }
            }

        }
    }

}

@Composable
fun JobList(
    jobs : List<Job>,
    onEvent : (JobEvent) -> Unit,
    appState : CareerBoastAppState
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState
    ) {
        items(
            items = jobs,
            key = { job -> job.id }
        ) { job ->

            JobItem(
                title = job.title,
                logoCompany = job.logoCompany,
                status = job.status,
                subTitle = job.subTitle,
                onItemClick = {
                    onEvent(JobEvent.SelectedJob(job.id))
                    appState.navigate(Screen.DETAILS_JOB_SCREEN.route)
                },
                onClickFavorite = {
                    onEvent(JobEvent.ChangeFavorite(job))
                },
                isFavorite = job.favorite
            )

        }
    }

}

@Composable
fun JobFavoriteList(
    list : List<Job>,
    onEvent : (JobEvent) -> Unit,
    appState : CareerBoastAppState
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState
    ) {
        items(
            items = list,
            key = Job::id
        ) { job ->

            JobItem(
                title = job.title,
                logoCompany = job.logoCompany,
                status = job.status,
                subTitle = job.subTitle,
                onItemClick = {
                    onEvent(JobEvent.SelectedJob(job.id))
                    appState.navigate(Screen.DETAILS_JOB_SCREEN.route)
                },
                onClickFavorite = {
                    onEvent(JobEvent.ChangeFavorite(job))
                },
                isFavorite = job.favorite
            )

        }
    }

}

@Composable
fun JobItem(
    title : String,
    logoCompany : String,
    status : String,
    subTitle : String,
    onItemClick : () -> Unit,
    onClickFavorite : () -> Unit,
    isFavorite : Boolean
) {


    Row(
        modifier = Modifier
            .basisPadding()
            .fillMaxWidth()
            .height(87.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(logoCompany)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),

            )

        Spacer(modifier = Modifier.width(10.dp))

        Column {

            Text(
                text = title,
                color = Black,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subTitle,
                color = DarkBlue,
                style = MaterialTheme.typography.bodyMedium

            )
            Text(
                text = status,
                color = DarkBlue,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Box(
            modifier = Modifier
                .padding(end = 15.dp, top = 15.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = { onClickFavorite() }
            ) {
                Icon(
                    painter = if (isFavorite) {
                        painterResource(id = R.drawable.favorite)
                    } else {
                        painterResource(id = R.drawable.favorite_fill)
                    },
                    tint = Blue,
                    contentDescription = stringResource(id = R.string.favorite)
                )
            }
        }
    }


}

@Preview
@Composable
private fun JobScreenPrev() {

    JobItem(
        title = "Yandex interships",
        logoCompany = "",
        status = "Open",
        subTitle = "E-commerce development",
        onItemClick = { },
        onClickFavorite = { },
        isFavorite = true
    )

}



