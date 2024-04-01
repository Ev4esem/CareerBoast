package com.example.careerboast.view.screens.interview_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.composable.BackButtonBasic
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen


@Composable
fun InterviewListScreen(
    uiState : InterviewListUiState,
    onEvent : (InterviewListEvent) -> Unit,
    appState : CareerBoastAppState,
    onNavigation : (String, String) -> Unit,
) {

    Column {
        BackButtonBasic(
            onBackClick = {
                appState.popUp()
            },
            modifier = Modifier
                .basisPadding(),
            icon = R.drawable.action_back
        )
        Text(
            text = stringResource(id = R.string.choose_interview),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.basisPadding(),
            color = Black
        )
        if (!uiState.errorList.isNullOrBlank()) {
            CareerErrorScreen(
                errorText = uiState.errorList,
                onClickRetry = {
                    onEvent(InterviewListEvent.RefreshData)
                }
            )
        } else if (uiState.interviewListLoading) {
            CareerLoadingScreen()
        } else {
            InterviewList(
                interviewList = uiState.selectSpeciality,
                onNavigation = onNavigation
            )
        }



    }



}


@Composable
fun InterviewList(
    interviewList : List<Interview>,
    modifier : Modifier = Modifier,
    onNavigation : (String, String) -> Unit
) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier.basisPadding()
    ) {

        items(
            items = interviewList,
            key = { interview -> interview.id }
        ) { interview ->
            Log.d("InterviewListId", "${interview.id}")

            InterviewItem(
                level = interview.level,
                logoCompany = interview.logoCompany,
                nameCompany = interview.nameCompany,
                numberTime = interview.numberTime,
                time = interview.time,
                title = interview.title,
                onItemClick = {
                    onNavigation(interview.id,interview.totalTime)
                }
            )

        }

    }

}

@Composable
fun InterviewItem(
    level : String,
    logoCompany : String,
    nameCompany : String,
    numberTime : Int,
    time : String,
    title : String,
    onItemClick : () -> Unit,
    modifier : Modifier = Modifier
) {


    Row(
        modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {

        //todo Добавить прелоадер
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(logoCompany)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
                .size(70.dp)
                .clip(RoundedCornerShape(5.dp)),
        )

        Spacer(modifier = modifier.width(27.dp))

        Column {

            Text(
                text = title,
                fontSize = 24.sp,
                color = Black
            )
            Text(
                text = "$numberTime $time • $level • $nameCompany",
                color = DarkBlue
            )

        }


    }


}

@Preview
@Composable
private fun InterviewListScreenPrev() {

    InterviewItem(
        level = "Junior",
        logoCompany = "https://img2.teletype.in/files/11/aa/11aa3dda-8f9d-4de6-828f-844b9f534cd3.jpeg",
        nameCompany = "Yandex",
        numberTime = 20,
        time = "minute",
        title = "Android developer",
        onItemClick = { }
        )

}

