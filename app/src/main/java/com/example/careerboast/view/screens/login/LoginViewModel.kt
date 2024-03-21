package com.example.careerboast.view.screens.login

import com.example.careerboast.common.ext.isValidEmail
import com.example.careerboast.common.snackbar.SnackbarManager
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.login.RegisterAccountUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.view.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.careerboast.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val registerAccountUseCase : RegisterAccountUseCase,
    logService : LogService
) : CareerBoastViewModel(logService) {


    private var _uiState = MutableStateFlow(LoginUiState())
    val uiState  = _uiState.asStateFlow()


    private val email
        get() = _uiState.value.email

    private val password
        get() = _uiState.value.password


    fun onEmailChange(newValue: String) {
        _uiState.value = _uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = _uiState.value.copy(password = newValue)
    }

    fun onSingInClick(openAndPopUp : (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }
        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }


        launchCatching {
            registerAccountUseCase(email, password)
            openAndPopUp(Screen.SPECIALITY_SCREEN.route,Screen.LOGIN_SCREEN.route)
        }

    }

}


