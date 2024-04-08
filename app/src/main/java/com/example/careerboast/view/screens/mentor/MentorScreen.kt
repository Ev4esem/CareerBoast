package com.example.careerboast.view.screens.mentor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.composable.BasicButton
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.domain.model.mentors.Mentor
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.ui.theme.Grey
import com.example.careerboast.ui.theme.LavenderElementLight

@Composable
fun MentorScreen(
    uiState : MentorUiState,
    onNavigation : (String) -> Unit,
    onEvent : (MentorEvent) -> Unit
) {

    if (! uiState.error.isNullOrBlank()) {
        CareerErrorScreen(
            errorText = uiState.error.toString(),
            onClickRetry = {
                onEvent(MentorEvent.RefreshData)
            }
        )
    } else if (uiState.loading) {
        CareerLoadingScreen()
    } else {
        MentorList(
            mentorList = uiState.mentors,
            onEvent = onEvent,
            onNavigation = onNavigation
        )

    }

}


@Composable
fun MentorList(
    mentorList : List<Mentor>,
    onNavigation : (String) -> Unit,
    onEvent : (MentorEvent) -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(
            items = mentorList,
            key = Mentor::id
        ) { mentor ->

            MentorItem(
                mentor = mentor,
                onNavigation = onNavigation,
                onClickFavorite = {
                    onEvent(MentorEvent.ChangeFavorite(mentor))
                }
            )

        }

    }

}

@Composable
fun MentorItem(
    mentor : Mentor,
    onNavigation : (String) -> Unit,
    onClickFavorite : () -> Unit,
) {

    Column(

    ) {

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 22.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(mentor.imagePerson)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(23.dp))

            Column {

                Text(
                    text = mentor.name + " " + mentor.surname,
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = mentor.company + ", " + mentor.position,
                    color = DarkBlue
                )

            }

        }

        Text(
            text = stringResource(id = R.string.details),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 22.dp)

        )

        Spacer(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .height(1.dp)
                .background(Grey)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 22.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(R.string.experience),
                color = DarkBlue
            )

            Text(
                text = mentor.experience,
                fontWeight = FontWeight.Bold,

                )

        }

        Spacer(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .height(1.dp)
                .background(Grey)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 22.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(R.string.feedback),
                color = DarkBlue
            )

            Text(
                text = "${mentor.feedback}" + "/5",
                fontWeight = FontWeight.Bold,

                )

        }

        Spacer(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .height(1.dp)
                .background(Grey)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 22.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(R.string.price),
                color = DarkBlue
            )

            Text(
                text = "${mentor.price}" + mentor.currency + "/" + mentor.time,
                fontWeight = FontWeight.Bold,

                )

        }

        Spacer(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .height(1.dp)
                .background(Grey)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            BasicButton(
                text = R.string.apply,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                action = { onNavigation(mentor.id) }
            )

            BasicButton(
                text = if (mentor.favorite) {
                    R.string.delete
                } else {
                    R.string.save
                },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                colorBackground = LavenderElementLight,
                colorText = Black,
                action = {
                    onClickFavorite()
                }
            )

        }

    }

}

@Preview
@Composable
private fun MentorScreenPrev() {

    val list = listOf(
        Mentor(
            company = "ABC Company",
            currency = "USD",
            experience = "5 years",
            feedback = 4.5,
            imagePerson = "https://example.com/image.jpg",
            name = "John",
            position = "Senior Developer",
            price = 50,
            surname = "Doe",
            time = "hour",
            id = "12523567890",
            favorite = false
        ),
        Mentor(
            company = "ABC Company",
            currency = "USD",
            experience = "5 years",
            feedback = 4.5,
            imagePerson = "https://example.com/image.jpg",
            name = "John",
            position = "Senior Developer",
            price = 50,
            surname = "Doe",
            time = "hour",
            id = "1234552367890",
            favorite = false
        ),
        Mentor(
            company = "ABC Company",
            currency = "USD",
            experience = "5 years",
            feedback = 4.5,
            imagePerson = "https://example.com/image.jpg",
            name = "John",
            position = "Senior Developer",
            price = 50,
            surname = "Doe",
            time = "hour",
            id = "1234567890",
            favorite = false
        ),
        Mentor(
            company = "ABC Company",
            currency = "USD",
            experience = "5 years",
            feedback = 4.5,
            imagePerson = "https://example.com/image.jpg",
            name = "John",
            position = "Senior Developer",
            price = 50,
            surname = "Doe",
            time = "hour",
            id = "1234567890",
            favorite = false
        )
    )


    MentorList(mentorList = list, onNavigation = {}) {

    }


}

