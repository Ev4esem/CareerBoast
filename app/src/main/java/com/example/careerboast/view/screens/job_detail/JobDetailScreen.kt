package com.example.careerboast.view.screens.job_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.composable.BackButtonBasic
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.common.composable.RatingBar
import com.example.careerboast.domain.model.jobs.FeedbackJob
import com.example.careerboast.domain.model.jobs.Info
import com.example.careerboast.domain.model.jobs.InternshipsDetail
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.ui.theme.Grey
import com.example.careerboast.ui.theme.LavenderElement
import com.example.careerboast.ui.theme.White
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.screens.job.JobEvent
import com.example.careerboast.view.screens.mentor_detail.SkillItem

@Composable
fun JobDetailScreen(
    modifier : Modifier = Modifier,
    uiState : JobDetailUiState,
    appState : CareerBoastAppState,
    onEvent : (JobDetailEvent) -> Unit
) {

    if (! uiState.error.isNullOrBlank()) {

        CareerErrorScreen(
            errorText = uiState.error.toString(),
            onClickRetry = {
                onEvent(JobDetailEvent.RefreshData)
            }
        )

    } else if (uiState.loading) {
        CareerLoadingScreen()
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {

            item {
                Box {
                    BackButtonBasic(
                        onBackClick = {
                            appState.popUp()
                        },
                        icon = R.drawable.cross
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.jobDetail.logoCompany)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = uiState.jobDetail.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = uiState.jobDetail.nameCompany,
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkBlue
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${uiState.jobDetail.status} until ${uiState.jobDetail.data}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkBlue
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    Button(

                        onClick = {
                            appState.navigate(Screen.WEB_VIEW_SCREEN.route + "/${uiState.jobDetail.url}")
                        },
                        colors = ButtonDefaults.buttonColors(Blue),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier
                            .height(32.dp)
                            .width(105.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.apply_now),
                            color = White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,

                            )
                    }

                }

                Spacer(modifier = Modifier.height(32.dp))

            }

            item {
                InfoList(
                    info = uiState.jobDetail.info,
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(
                items = uiState.jobDetail.feedback_job
            ) { feedback ->
                FeedbackItem(
                    imageUrl = feedback.imagePerson,
                    title = feedback.name,
                    description = feedback.description,
                    rating = feedback.feedback
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }


            items(uiState.jobDetail.internships_detail) { item ->
                InternshipsDetailItem(internshipsDetail = item)
            }

        }
    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InfoList(
    info : List<Info>,
    modifier : Modifier = Modifier
) {

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {

        info.forEach { item ->
            CardInfo(
                title = item.title,
                info = item.info
            )
        }

    }

}

@Composable
fun InternshipsDetailItem(
    internshipsDetail : InternshipsDetail
) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = internshipsDetail.title)
        Text(text = internshipsDetail.info)

    }
}

@Composable
fun CardInfo(
    title : String,
    info : String,
) {

    Surface(
        color = LavenderElement,
        modifier = Modifier
            .width(170.dp),
        shape = MaterialTheme.shapes.medium
    ) {

        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = title,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = info,
                style = MaterialTheme.typography.titleLarge
            )
        }

    }

}

@Composable
fun FeedbackItem(
    imageUrl : String,
    title : String,
    description : String,
    rating : Int,
    modifier : Modifier = Modifier
) {

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title
            )

        }
        Spacer(modifier = Modifier.height(12.dp))
        RatingBar(rating = rating)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = description)

    }

}


@Preview(heightDp = 1500)
@Composable
private fun RatingBarPrev() {

    Column {


    }


}