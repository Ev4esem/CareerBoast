package com.example.careerboast.view.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.careerboast.common.composable.BasicButton
import com.example.careerboast.common.composable.EmailField
import com.example.careerboast.common.composable.PasswordField
import com.example.careerboast.common.ext.basicButton
import com.example.careerboast.common.ext.fieldModifier
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.CareerBoastTheme
import com.example.careerboast.R.string as AppText

@Composable
fun LoginScreen(
    openAndPopUp : (String, String) -> Unit,
    viewModel : LoginViewModel,
    uiState : LoginUiState
) {

    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSingInClick(openAndPopUp) }
    )

}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth().basicButton(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(AppText.sign_up),
                color = Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
                )
        }
        Spacer(modifier = Modifier.height(20.dp))
        EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, onPasswordChange, Modifier.fieldModifier())

        BasicButton(
            text = AppText.register,
            modifier = Modifier
                .basicButton()
                .height(61.dp)
        ) {
            onSignInClick()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState(
        email = "email@test.com"
    )

    CareerBoastTheme {
        LoginScreenContent(
            uiState = uiState,
            onEmailChange = { },
            onPasswordChange = { },
            onSignInClick = { },
        )
    }
}