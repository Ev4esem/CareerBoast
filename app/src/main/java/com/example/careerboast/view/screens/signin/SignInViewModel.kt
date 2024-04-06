package com.example.careerboast.view.screens.signin

import com.example.careerboast.domain.repositories.LogService
import com.example.careerboast.domain.use_cases.login.SignInUseCase
import com.example.careerboast.utils.CareerBoastViewModel
import com.example.careerboast.utils.EffectHandler
import com.example.careerboast.utils.collectAuthAsResult
import com.example.careerboast.view.navigation.Screen
import com.example.careerboast.view.screens.login.LoginEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase : SignInUseCase,
    logService : LogService
) : CareerBoastViewModel(logService), EffectHandler<SignInEffect> {

    override val effectChannel : Channel<SignInEffect> = Channel()

    private var _uiState = MutableStateFlow(SignInUiState())
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

    fun onSignInClick(openAndPopUp : (String, String) -> Unit) {
        launchCatching {
            signInUseCase(email, password).collectAuthAsResult(
                onSuccess = {
                    openAndPopUp(Screen.SPECIALITY_SCREEN.route, Screen.SIGN_IN_SCREEN.route)
                },
                onError = { exception, message ->
                    sendEffect(SignInEffect.ShowToast(message ?: "Unknown error"))
                }
            )
        }
    }

}

data class SignInUiState(
    val email : String = "",
    val password : String = "",
    val error : String? = null,
)

sealed interface SignInEffect {

    data class ShowToast(
        val message : String
    ) : SignInEffect

}