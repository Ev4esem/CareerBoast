package com.example.careerboast.view.screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerboast.domain.use_cases.login.SignInUseCase
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
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel(), EffectHandler<SignInEffect> {

    override val effectChannel: Channel<SignInEffect> = Channel()

    private var _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()


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

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
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
    val email: String = "",
    val password: String = "",
    val error: String? = null,
)

sealed interface SignInEffect {

    data class ShowToast(
        val message: String
    ) : SignInEffect

}