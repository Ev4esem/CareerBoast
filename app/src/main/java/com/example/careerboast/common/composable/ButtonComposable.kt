package com.example.careerboast.common.composable

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.careerboast.R
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.ui.theme.White

@Composable
fun BasicButton(
    @StringRes text: Int,
    modifier: Modifier,
    colorText : Color = White,
    colorBackground : Color = Blue,
    action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = colorBackground
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = stringResource(text),
            fontSize = 16.sp,
            color = colorText
            )
    }
}

@Composable
fun BackButtonBasic(
    onBackClick : () -> Unit,
    modifier : Modifier = Modifier
) {

    IconButton(
        onClick = { onBackClick() },
        modifier = modifier
    ) {

        Icon(
            painter = painterResource(id = R.drawable.action_back),
            contentDescription = stringResource(R.string.action_back)
        )

    }

}