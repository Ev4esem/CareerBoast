package com.example.careerboast.view.screens.job_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
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
import com.example.careerboast.domain.model.jobs.FeedbackJob
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.DarkBlue
import com.example.careerboast.ui.theme.Grey
import com.example.careerboast.ui.theme.LavenderElement
import com.example.careerboast.ui.theme.White
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.navigation.rememberCareerBoastAppState

@Composable
fun JobDetailScreen(
    modifier : Modifier = Modifier,
    title : String,
    imageUrl : String,
    nameCompany : String,
    status : String,
    data : String,
    ratingCompany : Float,
    location : String,
    currency : String,
    compensation : Int,
    url : String,
    appState : CareerBoastAppState,
    review : String,
    jobType : String,
    qualification : String,
    startDate : String,
    list : List<FeedbackJob>
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

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
                .data(imageUrl)
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
            text = title,
            style = MaterialTheme.typography.bodyLarge,
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
                    text = nameCompany,
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$status until $data",
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkBlue
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            Button(

                onClick = {
                    appState.navigate(Screen.WEB_VIEW_SCREEN.route + "/$url")
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
        ) {
            CardInfo(
                title = stringResource(id = R.string.rating),
                info = "$ratingCompany",
                modifier = Modifier
                    .width(170.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            CardInfo(
                title = stringResource(id = R.string.location),
                info = location,
                modifier = Modifier
                    .width(170.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
        ) {
            CardInfo(
                title = stringResource(id = R.string.compensation),
                info = currency + "$compensation",
                modifier = Modifier
                    .width(170.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            CardInfo(
                title = stringResource(id = R.string.reviews),
                info = review,
                modifier = Modifier
                    .width(170.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

            FeedbackList(
                list = list,
                modifier = Modifier.height(400.dp)
            )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.internship_details),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.job_type) + ": ")
            Text(text = jobType)

        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.qualifications) + ": ")
            Text(text = qualification)

        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.start_date) + ": ")
            Text(text = startDate)

        }

        Spacer(modifier = Modifier.height(16.dp))


    }






}

@Composable
fun FeedbackList(
    list : List<FeedbackJob>,
    modifier : Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(list) { feedback ->
            FeedbackItem(
                imageUrl = feedback.imagePerson,
                title = feedback.name,
                description = feedback.description,
                rating = feedback.feedback
            )
        }
    }

}

@Composable
fun CardInfo(
    title : String,
    info : String,
    modifier : Modifier = Modifier
) {

    Surface(
        color = LavenderElement,
        modifier = modifier
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


@Composable
fun RatingBar(rating : Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            val isFilled = index < rating
            Star(isFilled)
        }
    }
}

@Composable
fun Star(isFilled : Boolean) {
    val starColor = if (isFilled) Blue else Grey
    Icon(
        painter = if (isFilled) {
            painterResource(id = R.drawable.star_fill_feedback)
        } else {
            painterResource(id = R.drawable.star_feedback)
        },
        contentDescription = null,
        tint = starColor
    )
}

@Preview(heightDp = 1500)
@Composable
private fun RatingBarPrev() {

    Column {
       JobDetailScreen(
           title = "Software Engineer, Internship(Summer 2023)",
           imageUrl = "",
           nameCompany = "Facebook",
           status = "Open",
           data = "01/31",
           ratingCompany = 4.9f,
           location = "Menlo Park,CA",
           currency = "$",
           compensation = 12000,
           review = "1.5k",
           jobType = "Full time",
           qualification = "Junior year of college or above",
           startDate = "June 2023",
           list = listOf(
               FeedbackJob(
                   name = "Eduardo",
                   feedback = 4,
                   description = "Great experience, I learned a lot and made many friends."
               ),
               FeedbackJob(
                   name = "Eduardo",
                   feedback = 4,
                   description = "Great experience, I learned a lot and made many friends."
               ),
               FeedbackJob(
                   name = "Eduardo",
                   feedback = 4,
                   description = "Great experience, I learned a lot and made many friends."
               )
           ),
           url = "",
           appState = rememberCareerBoastAppState()
       )

    }


}