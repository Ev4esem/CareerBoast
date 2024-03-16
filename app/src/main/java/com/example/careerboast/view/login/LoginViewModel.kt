package com.example.careerboast.view.login

import androidx.compose.material3.Snackbar
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.careerboast.common.ext.isValidEmail
import com.example.careerboast.common.snackbar.SnackbarManager
import com.example.careerboast.domain.repositories.AccountService
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.R.string as AppText

class LoginViewModel(
    private val accountService : AccountService,
    logService : LogService
) : CareerBoastViewModel(logService) {

    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun onSingInClick(openAndPopUp : (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }
        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }


        //todo Navigation in interview screen
        launchCatching {
            accountService.register(email, password)
        }

    }

}