package com.example.careerboast.view.screens.mentor_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.careerboast.R
import com.example.careerboast.common.composable.BackButtonBasic
import com.example.careerboast.common.composable.BasicButton
import com.example.careerboast.common.composable.CareerErrorScreen
import com.example.careerboast.common.composable.CareerLoadingScreen
import com.example.careerboast.common.composable.RatingBar
import com.example.careerboast.common.ext.basisPadding
import com.example.careerboast.domain.model.mentors.Contact
import com.example.careerboast.domain.model.mentors.FeedbackMentor
import com.example.careerboast.domain.model.mentors.Skills
import com.example.careerboast.domain.model.mentors.SocialMedia
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.ui.theme.LavenderElement
import com.example.careerboast.view.navigation.CareerBoastAppState

@Composable
fun MentorDetailScreen(
    appState : CareerBoastAppState,
    uiState : MentorDetailUiState,
    onEvent : (MentorDetailEvent) -> Unit
) {

    if (!uiState.error.isNullOrBlank()) {
        CareerErrorScreen(
            errorText = uiState.error
        ) {
            onEvent(MentorDetailEvent.RefreshData)
        }
    } else if(uiState.loading) {
        CareerLoadingScreen()
    } else {

        LazyColumn(
            modifier = Modifier.padding(15.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BackButtonBasic(
                        onBackClick = {
                            appState.popUp()
                        },
                        icon = R.drawable.cross,
                    )
                }

            }

            item {
                TopMentorDetailScreen(
                    imageUrl = uiState.mentorDetail.imagePerson,
                    name = uiState.mentorDetail.name,
                    year = uiState.mentorDetail.year,
                    position = uiState.mentorDetail.position,
                    location = uiState.mentorDetail.location
                )
            }

            item {
                InfoText(text = stringResource(id = R.string.about) + " " + uiState.mentorDetail.name)

                Text(text = uiState.mentorDetail.description)

            }

            item {
                InfoText(text = stringResource(id = R.string.specialties))

            }

            item {
                SkillsList(
                    skills = uiState.mentorDetail.skills,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                InfoText(text = stringResource(id = R.string.contact))

            }

            items(uiState.mentorDetail.contacts) { contact ->
                ContactItem(contact = contact)
            }

            item {
                InfoText(text = stringResource(id = R.string.social_media))

            }

            items(uiState.mentorDetail.socialMedia) { item ->
                SocialMediaItem(socialMedia = item)
            }

            item {
                InfoText(text = stringResource(id = R.string.pricing))

            }

            item {
                PriceItem(
                    title = uiState.mentorDetail.time,
                    imageUrl = uiState.mentorDetail.imageUrlPrice,
                    price = uiState.mentorDetail.price,
                    currency = uiState.mentorDetail.currency,
                    numberTime = uiState.mentorDetail.numberTime
                )

            }

            item {
                InfoText(text = stringResource(id = R.string.Review))
            }

            items(uiState.mentorDetail.feedbacks) { item ->
                FeedbackItem(feedbackMentor = item)
            }

        }

    }

}

@Composable
fun PriceItem(
    title : String,
    numberTime : Int,
    imageUrl : String,
    price : Int,
    currency : String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "$numberTime $title call"
            )

        }

        Text(text = currency + price)

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsList(
    skills : List<Skills>,
    modifier : Modifier = Modifier
) {

    FlowRow(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {

        skills.forEach { skill ->
            SkillItem(title = skill.title)
        }

    }

}

@Composable
fun TopMentorDetailScreen(
    imageUrl : String,
    name : String,
    year : Int,
    position : String,
    location : String
) {

    Column {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {

            Text(
                text = "$name, $year",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = position,
                color = DarkBlue,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = location,
                color = DarkBlue,
                style = MaterialTheme.typography.bodyLarge
            )


        }
        Spacer(modifier = Modifier.height(16.dp))

        BasicButton(
            text = R.string.message,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colorText = Black,
            colorBackground = LavenderElement,
            action = {

            }
        )
    }

}


@Composable
fun SkillItem(
    title : String
) {

    Surface(
        color = LavenderElement,
        modifier = Modifier.padding(top = 10.dp, end = 10.dp),
        shape = MaterialTheme.shapes.small
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

        }

    }

}

@Composable
fun InfoText(
    text : String
) {

    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 15.dp)
    )

}

@Composable
fun ContactItem(
    contact : Contact
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {


        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(contact.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {

            Text(
                text = contact.title,
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = contact.subTitle,
                color = DarkBlue,
                style = MaterialTheme.typography.bodyMedium
            )

        }


    }

}

@Composable
fun SocialMediaItem(
    socialMedia : SocialMedia
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(socialMedia.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))

        Text(text = socialMedia.title)

    }

}


@Composable
fun FeedbackItem(
    feedbackMentor : FeedbackMentor
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .basisPadding()
    ) {

        Text(
            text = feedbackMentor.title,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = feedbackMentor.data,
            color = DarkBlue,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        RatingBar(rating = feedbackMentor.rating)

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = feedbackMentor.description)

    }

}


@Preview
@Composable
private fun MentorDetailScreenPrev() {

    val skillsList = listOf(
        Skills(id = 1, title = "Java"),
        Skills(id = 2, title = "Kotlin"),
        Skills(id = 3, title = "Android"),
        Skills(id = 4, title = "Product Management"),
        Skills(id = 5, title = "I just cool"),
    )

    val socialMediaList = listOf(
        SocialMedia(
            id = 1,
            imageUrl = "https://example.com/linkedin.png",
            title = "LinkedIn",
            url = "https://www.linkedin.com"
        ),
        SocialMedia(
            id = 2,
            imageUrl = "https://example.com/twitter.png",
            title = "Twitter",
            url = "https://twitter.com"
        )
    )

    val contactList = listOf(
        Contact(
            id = 1,
            imageUrl = "https://example.com/telegram.png",
            subTitle = "Username",
            title = "Telegram"
        ),
        Contact(
            id = 2,
            imageUrl = "https://example.com/skype.png",
            subTitle = "Username",
            title = "Skype"
        )
    )

    val feedbackList = listOf(
        FeedbackMentor(
            id = 1,
            title = "Отзыв 1",
            description = "Описание отзыва 1",
            rating = 5,
            data = "Дата отзыва 1"
        ),
        FeedbackMentor(
            id = 2,
            title = "Отзыв 2",
            description = "Описание отзыва 2",
            rating = 4,
            data = "Дата отзыва 2"
        ),
        FeedbackMentor(
            id = 3,
            title = "Отзыв 3",
            description = "Описание отзыва 3",
            rating = 3,
            data = "Дата отзыва 3"
        )
    )



}


