package com.example.careerboast.view.screens.login

import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.login.RegisterAccountUseCase
import com.example.careerboast.domain.use_cases.login.SignInUseCase
import com.example.careerboast.domain.use_cases.login.SignOutUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.collectAuthAsResult
import com.example.careerboast.view.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val registerAccountUseCase : RegisterAccountUseCase,
    private val signOutUseCase : SignOutUseCase,
    private val signInUseCase : SignInUseCase,
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
        _uiState.value = _uiState.value.copy(email = newValue.trim())
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = _uiState.value.copy(password = newValue.trim())
    }

    fun onSingUpClick(openAndPopUp : (String, String) -> Unit) {
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

    fun onSignOut(onClick : (String) -> Unit) {
        viewModelScope.launch {
            onClick(Screen.LOGIN_SCREEN.route)
            signOutUseCase()
        }
    }

}


