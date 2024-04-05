package com.example.careerboast.view.screens.interview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.careerboast.R
import com.example.careerboast.common.composable.BackButtonBasic
import com.example.careerboast.common.composable.BasicButton
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.common.composable.ConfirmationDialog
import com.example.careerboast.common.composable.FinishedDialog
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.common.ext.smallSpacer
import com.example.careerboast.common.ext.spacer
import com.example.careerboast.domain.model.interviews.Answer
import com.example.careerboast.domain.model.interviews.AnswerResult
import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.LavenderElement
import com.example.careerboast.utils.ANSWER_STATE
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


@Composable
fun InterviewScreen(
    uiState : InterviewUiState,
    appState : CareerBoastAppState,
    answerResult : List<AnswerResult>,
    onEvent : (InterviewEvent) -> Unit,
    onNavigation : (String) -> Unit
) {

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val type = Types.newParameterizedType(List::class.java, AnswerResult::class.java)
    val jsonAdapter = moshi.adapter<List<AnswerResult>>(type)
    val userJson = jsonAdapter.toJson(answerResult)

    FinishedDialog(
        title = stringResource(R.string.congratulations),
        message = stringResource(id = R.string.dialog_answered_questions),
        state = uiState.finishDialogIsVisible,
        onConfirm = {
            onNavigation(
                userJson
            )
            onEvent(InterviewEvent.FinishedInterview)
        },
        onDismiss = {
            onEvent(InterviewEvent.ChangeFinishDialogState(false))
            appState.navController.navigateUp()
        }
    )



    if (uiState.isTimerFinished) {

        FinishedDialog(
            title = stringResource(R.string.dialog_title),
            message = stringResource(R.string.dialog_message),
            onConfirm = {
                onNavigation(
                    userJson
                )
                onEvent(InterviewEvent.FinishedInterview)
            },
            onDismiss = {
                appState.navController.navigateUp()
            }
        )

    }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (uiState.loading) {

            CareerLoadingScreen()

        } else if (uiState.error != null) {

            CareerErrorScreen(
                errorText = uiState.error.toString(),
                onClickRetry = {
                    onEvent(InterviewEvent.RefreshData)
                }
            )

        } else if (uiState.selectQuestion.isNotEmpty()) {

            val currentQuestion = uiState.selectQuestion[uiState.currentQuestionIndex]
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {


                QuizTopAppBar(
                    questionIndex = uiState.selectQuestion.indexOf(currentQuestion),
                    totalQuestionsCount = uiState.selectQuestion.size,
                    minute = uiState.timerData.minutes,
                    second = uiState.timerData.seconds,
                    appState = appState
                )

                Spacer(modifier = Modifier.height(50.dp))

                QuestionList(
                    question = currentQuestion,
                    onAnswerSelected = { selectedAnswerIndex ->
                        onEvent(
                            InterviewEvent.SelectAnswer(
                                currentQuestion.id,
                                selectedAnswerIndex
                            )
                        )
                    }
                )



                BasicButton(
                    action = { onEvent(InterviewEvent.SubmitAnswer) },
                    modifier = Modifier.padding(16.dp),
                    text = R.string.submit
                )

                Spacer(modifier = Modifier.smallSpacer())


            }
        }
    }
}

@Composable
private fun QuizTopAppBar(
    questionIndex : Int,
    totalQuestionsCount : Int,
    minute : Long,
    second : Long,
    appState : CareerBoastAppState,
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        ConfirmationDialog(
            title = stringResource(id = R.string.exit_with_interview),
            message = stringResource(id = R.string.question_finished_inter),
            state = showDialog,
            onConfirm = {
                appState.navController.navigateUp()
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .align(Alignment.Start)
        ) {

            BackButtonBasic(
                onBackClick = {
                    showDialog = true
                },
                modifier = Modifier.basisPadding(),
                icon = R.drawable.cross
            )

        }

        Spacer(modifier = Modifier.smallSpacer())

        Timer(
            minutes = minute,
            second = second
        )


        val progress = (questionIndex + 1).toFloat() / totalQuestionsCount.toFloat()

        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = ""
        )

        Spacer(modifier = Modifier.smallSpacer())

        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            trackColor = Black.copy(alpha = 0.12f)
        )
    }
}


@Composable
fun QuestionList(
    question : Question,
    onAnswerSelected : (Int) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .basisPadding()
    ) {
        Text(
            text = question.question,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        AnswerList(
            list = question.answers,
            onAnswerSelected = onAnswerSelected
        )

    }

}

@Composable
fun AnswerList(
    list : List<Answer>,
    onAnswerSelected : (Int) -> Unit,
) {

    var selectedAnswer by remember {
        mutableStateOf(- 1)
    }
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(
            items = list,
            key = Answer::id
        ) { answer ->
            AnswerItem(
                title = answer.title,
                selected = selectedAnswer == answer.id,
                onOptionSelected = {
                    selectedAnswer = answer.id
                    onAnswerSelected(answer.id)
                }
            )
        }

    }

}

@Composable
fun AnswerItem(
    title : String,
    selected : Boolean,
    onOptionSelected : () -> Unit
) {


    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        ),

        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .selectable(
                selected,
                onClick = onOptionSelected,
                role = Role.RadioButton
            ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(Modifier.padding(8.dp)) {
                RadioButton(selected, onClick = null)
            }

            Spacer(modifier = Modifier.spacer())

            Text(
                text = title,
                color = Black,
                style = MaterialTheme.typography.bodyMedium

            )
        }


    }

}

@Composable
fun Timer(minutes : Long, second : Long) {

    Surface(
        color = LavenderElement,
        shape = MaterialTheme.shapes.medium
    ) {

        Row(
            modifier = Modifier
                .width(180.dp)
                .height(60.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$minutes:$second",
                style = MaterialTheme.typography.titleLarge
            )

        }

    }

}


@Preview
@Composable
private fun InterviewScreenPrev() {

    Column {

        Timer(minutes = 34, second = 22)

    }




}
