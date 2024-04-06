package com.example.careerboast.view.screens.feedback

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.composable.BackButtonBasic
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.domain.model.interviews.StudyMaterial
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.ui.theme.LavenderElement
import com.example.careerboast.ui.theme.LavenderElementLight
import com.example.careerboast.ui.theme.LavenderText
import com.example.careerboast.utils.SPECIALITY_ID
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.navigation.buildWebViewRoute
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun FeedbackScreen(
    uiState : FeedbackUiState,
    appState : CareerBoastAppState,
    onEvent : (FeedbackEvent) -> Unit
) {

    BackHandler {
        appState.clearAndNavigate(Screen.SPECIALITY_SCREEN.route)
    }

    if (! uiState.error.isNullOrBlank()) {
        CareerErrorScreen(
            errorText = uiState.error
        ) {
            onEvent(FeedbackEvent.RefreshData)
        }
    } else if (uiState.loading) {
        CareerLoadingScreen()
    } else {



        Scaffold(
            topBar = {
                TopBarFeedback(
                    correctAnswersCount = uiState.correctAnswer,
                    totalQuestionsCount = uiState.correctAnswer + uiState.incorrectAnswer,
                    onClick = {
                        appState.clearAndNavigate(Screen.SPECIALITY_SCREEN.route)
                    }
                )
            }
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.basisPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CountAnswer(
                        icon = R.drawable.tick,
                        title = R.string.correct,
                        count = uiState.correctAnswer,
                        contentDescription = R.string.correct
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    CountAnswer(
                        icon = R.drawable.cross,
                        title = R.string.incorrect,
                        count = uiState.incorrectAnswer,
                        contentDescription = R.string.incorrect
                    )

                }


                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                )

                if (uiState.studyList.isNotEmpty()) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .basisPadding(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.recommended_study),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                    StudyMaterialList(
                        list = uiState.studyList,
                        appState = appState
                    )

                }

            }



        }
    }


}

@Composable
fun CountAnswer(
    icon : Int,
    title : Int,
    count : Int,
    contentDescription : Int
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 1.dp,
            color = LavenderElement
        ),

    ) {
        Row(
            modifier = Modifier
                .height(58.dp)
                .width(175.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(contentDescription)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "$count" + " " + stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge
                )

            }

        }
    }

}

@Composable
fun TopBarFeedback(
    correctAnswersCount : Int,
    totalQuestionsCount : Int,
    onClick : () -> Unit
) {

    val progress = correctAnswersCount.toFloat() / totalQuestionsCount.toFloat()

    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .basisPadding()
        ) {
            BackButtonBasic(
                onBackClick = onClick,
                icon = R.drawable.cross,
            )
        }

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .basisPadding(),
            color = Blue,
            strokeCap = StrokeCap.Round,
            trackColor = LavenderElement,
        )

    }


}

@Composable
fun StudyMaterialList(
    list : List<StudyMaterial>,
    appState : CareerBoastAppState
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(
            items = list,
            key = StudyMaterial::id
        ) { item ->

            StudyItem(
                imageUrl = item.imageUrl,
                title = item.title,
                task = item.task,
                onClick = {

                    appState.navigate(buildWebViewRoute(item.url))

                }
            )

        }
    }

}

@Composable
fun StudyItem(
    imageUrl : String,
    title : String,
    task : String,
    onClick : () -> Unit,
    modifier : Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(180.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Black
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = task,
            style = MaterialTheme.typography.bodyMedium,
            color = DarkBlue
        )

    }

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewContainer(url : String) {
    // todo Не поддерживается
    val state = rememberWebViewState(url)

    WebView(
        state = state,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}

@Preview
@Composable
private fun FeedbackScreenPrev() {

    CountAnswer(icon = R.drawable.tick, title = R.string.correct, count = 5, contentDescription = R.string.correct)

    val progress = 4.toFloat() / 10.toFloat()


    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .basisPadding(),
        color = Blue,
        strokeCap = StrokeCap.Round,
        trackColor = LavenderElement.copy(alpha = 0.12f),
    )

}
