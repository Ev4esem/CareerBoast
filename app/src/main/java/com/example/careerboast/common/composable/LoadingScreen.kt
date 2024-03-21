package com.example.careerboast.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CareerLoadingScreen(
    modifier : Modifier = Modifier
) {

    Surface(
        modifier = modifier
    ) {

        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )

        }

    }


}