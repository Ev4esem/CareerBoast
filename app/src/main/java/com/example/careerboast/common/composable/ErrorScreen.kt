package com.example.careerboast.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.careerboast.R


@Composable
fun CareerErrorScreen(
    modifier : Modifier = Modifier,
    errorText : String,
    onClickRetry : () -> Unit
) {

    Surface(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.primary
    ) {

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(15.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_no_connection),
                contentDescription = stringResource(id = R.string.generic_error)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // todo
            Text(
                text = errorText,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = onClickRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(
                    vertical = 15.dp,
                    horizontal = 25.dp,
                )
            ) {

                Text(
                    text = stringResource(id = R.string.try_again),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )

            }

        }


    }

}