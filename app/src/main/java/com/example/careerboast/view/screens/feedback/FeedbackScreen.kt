package com.example.careerboast.view.screens.feedback

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
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
import com.example.careerboast.utils.ShimmerBrush
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.navigation.buildWebViewRoute
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun FeedbackScreen(
    uiState: FeedbackUiState,
    appState: CareerBoastAppState,
    onEvent: (FeedbackEvent) -> Unit
) {

    BackHandler {
        appState.clearAndNavigate(Screen.SPECIALITY_SCREEN.route)
    }

    if (!uiState.error.isNullOrBlank()) {
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
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
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
    icon: Int,
    title: Int,
    count: Int,
    contentDescription: Int
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
    correctAnswersCount: Int,
    totalQuestionsCount: Int,
    onClick: () -> Unit
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
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .basisPadding(),
            color = Blue,
            strokeCap = StrokeCap.Round,
            trackColor = LavenderElement,
        )

        Spacer(modifier = Modifier.height(10.dp))


    }
}

@Composable
fun StudyMaterialList(
    list: List<StudyMaterial>,
    appState: CareerBoastAppState
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
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
                    val urlEncode = Uri.encode(item.url)
                    appState.navigate(buildWebViewRoute(urlEncode))
                }
            )
        }
    }
}

@Composable
fun StudyItem(
    imageUrl: String,
    title: String,
    task: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(180.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                loading = {
                    ShimmerBrush()
                }
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
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewContainer(url: String) {
    // todo Не поддерживается
    val state = rememberWebViewState(url)
    val loading = state.loadingState

    WebView(
        state = state,
        onCreated = { it.settings.javaScriptEnabled = true }
    )

    if (loading is LoadingState.Loading) {
        LinearProgressIndicator(
            progress = loading.progress,
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
private fun FeedbackScreenPrev() {

    CountAnswer(
        icon = R.drawable.tick,
        title = R.string.correct,
        count = 5,
        contentDescription = R.string.correct
    )

    val progress = 4.toFloat() / 10.toFloat()


}
