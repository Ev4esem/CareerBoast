package com.example.careerboast.view.screens.speciality

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.domain.model.specialities.Speciality
import com.example.careerboast.ui.theme.White
import com.example.careerboast.utils.ShimmerBrush
import com.example.careerboast.view.navigation.drawer.AppBar


@Composable
fun SpecialitiesScreen(
    uiState: SpecialityUiState,
    onEvent: (SpecialitiesEvent) -> Unit,
    onNavigation: (String) -> Unit,
    drawerState: DrawerState
) {

    Scaffold(
        topBar = {
            AppBar(drawerState = drawerState)
        }
    ) {
        if (!uiState.error.isNullOrBlank()) {
            CareerErrorScreen(
                errorText = uiState.error.toString(),
                onClickRetry = {
                    onEvent(SpecialitiesEvent.RefreshData)
                }
            )
        } else if (uiState.specialityLoading) {
            CareerLoadingScreen()
        } else {
            SpecialitiesList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                specialityItems = uiState.specialityList,
                onNavigation = onNavigation
            )

        }
    }

}

@Composable
fun SpecialitiesList(
    modifier: Modifier = Modifier,
    specialityItems: List<Speciality>,
    onNavigation: (String) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(20.dp),
    ) {
        items(
            items = specialityItems,
            key = Speciality::id,
        ) { speciality ->
            SpecialityItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = speciality.title,
                image = speciality.imageUrl,
                onItemClick = {
                    onNavigation(speciality.id)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialityItem(
    title: String,
    image: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .aspectRatio(360 / 160f),
        shape = MaterialTheme.shapes.medium,
        onClick = onItemClick,
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                loading = {
                    ShimmerBrush()
                }
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp),
                text = title,
                color = White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
private fun SpecialitiesPrevScreen() {

    SpecialityItem(
        title = "Android",
        image = "https://www.ridus.ru/images/2018/7/12/790528/in_article_1a6b9ab070.webp",
        onItemClick = {}
    )
}
