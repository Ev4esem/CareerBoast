package com.example.careerboast.view.screens.login

import androidx.lifecycle.viewModelScope
import com.example.careerboast.common.ext.isValidEmail
import com.example.careerboast.common.snackbar.SnackbarManager
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.login.RegisterAccountUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.collectAuthAsResult
import com.example.careerboast.view.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.careerboast.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val registerAccountUseCase : RegisterAccountUseCase,
    logService : LogService
) : CareerBoastViewModel(logService), EffectHandler<LoginEffect> {

    override val effectChannel : Channel<LoginEffect> = Channel()

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
        launchCatching {
                registerAccountUseCase(email, password).collectAuthAsResult(
                    onSuccess = {
                        openAndPopUp(Screen.SPECIALITY_SCREEN.route,Screen.LOGIN_SCREEN.route)
                    },
                    onError = { exception, message ->
                        sendEffect(LoginEffect.ShowToast(message ?: "Unknown error"))
                    }
                )
            }

    }


}


