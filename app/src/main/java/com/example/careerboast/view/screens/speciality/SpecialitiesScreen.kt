package com.example.careerboast.view.screens.speciality

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.domain.model.specialities.Speciality
import com.example.careerboast.ui.theme.White
import com.example.careerboast.utils.SPECIALITY_ID
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.navigation.drawer.AppBar


@Composable
fun SpecialitiesScreen(
    uiState : SpecialityUiState,
    onEvent : (SpecialitiesEvent) -> Unit,
    onNavigation : (String) -> Unit,
    drawerState : DrawerState
) {

    Scaffold(
        topBar = {
            AppBar(drawerState = drawerState)
        }
    ) {
        if (! uiState.error.isNullOrBlank()) {
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
                modifier = Modifier.fillMaxSize().padding(it),
                specialityItems = uiState.specialityList,
                onNavigation = onNavigation
            )

        }
    }

}

@Composable
fun SpecialitiesList(
    modifier : Modifier = Modifier,
    specialityItems : List<Speciality>,
    onNavigation : (String) -> Unit
) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        items(
            items = specialityItems,
            key = { speciality -> speciality.id }
        ) { speciality ->
            SpecialityItem(
                title = speciality.title,
                image = speciality.imageUrl,
                onItemClick = {
                    onNavigation(speciality.id)
                }
            )
            Log.d("SpecialityId", "${speciality.id} in SpecialitiesScreen")
        }
    }
}


@Composable
fun SpecialityItem(
    title : String,
    image : String,
    onItemClick : () -> Unit,
    modifier : Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .width(360.dp)
                .height(169.dp)
                .clickable { onItemClick() }
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.preload),
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(15.dp))

            )

            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = modifier
                    .padding(start = 27.dp, bottom = 29.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = title,
                    color = White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
