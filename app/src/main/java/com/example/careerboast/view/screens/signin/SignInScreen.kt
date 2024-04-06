package com.example.careerboast.view.screens.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerboast.R
import com.example.careerboast.common.composable.BasicButton
import com.example.careerboast.common.composable.EmailField
import com.example.careerboast.common.composable.PasswordField
import com.example.careerboast.common.ext.basicButton
import com.example.careerboast.common.ext.fieldModifier
import com.example.careerboast.ui.theme.Black
import com.example.careerboast.ui.theme.Blue
import com.example.careerboast.view.navigation.CareerBoastAppState
import com.example.careerboast.view.navigation.Screen

@Composable
fun SignInScreen(
    openAndPopUp : (String, String) -> Unit,
    viewModel : SignInViewModel,
    appState : CareerBoastAppState,
    uiState : SignInUiState
) {

    SignInScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
        onClickText = { appState.navigateBasic(Screen.LOGIN_SCREEN.route) }
    )

}

@Composable
fun SignInScreenContent(
    modifier : Modifier = Modifier,
    uiState : SignInUiState,
    onClickText : () -> Unit,
    onEmailChange : (String) -> Unit,
    onPasswordChange : (String) -> Unit,
    onSignInClick : () -> Unit,
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
            modifier = modifier
                .fillMaxWidth()
                .basicButton(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                color = Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, onPasswordChange, Modifier.fieldModifier())

        BasicButton(
            text = R.string.sign_in,
            modifier = Modifier
                .basicButton()
                .height(61.dp)
        ) {
            onSignInClick()
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row {
            Text(
                text = stringResource(id = R.string.don_t_have_account)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(modifier = Modifier.clickable {
                onClickText()
            }) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    color = Blue,
                )
            }
        }


    }
}