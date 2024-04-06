package com.example.careerboast.utils

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


sealed class ValidationAuth<out T> {

    data class Success<out T>(
        val data: T
    ): ValidationAuth<T>()

    data class Failure(
        val e: Exception,
        val message : String?
    ): ValidationAuth<Nothing>()

}

// Автоматическое обновление трех states
fun <T> Flow<T>.asAuthResult() : Flow<ValidationAuth<T>> {
    return this
        .map<T, ValidationAuth<T>> { ValidationAuth.Success(it) }
        .catch { ex ->
            val exception = if (ex is Exception) {
                ex // Если ex уже является Exception, используйте его
            } else {
                Exception(ex) // Преобразуйте Throwable в Exception
            }
            emit(
                ValidationAuth.Failure(
                    e = exception,
                    message = handleException(exception)
                )
            )
        }
}
suspend fun <T> Flow<T>.collectAuthAsResult(
    onSuccess : suspend (T) -> Unit = {},
    onError : suspend (exception : Throwable?, message : String?) -> Unit = {_ ,_ ->},
) {
    asAuthResult().collect { result ->

        when(result) {
            is ValidationAuth.Success -> onSuccess(result.data)
            is ValidationAuth.Failure -> {
                val authError = handleAuthException(result.e)
                onError(result.e, authError)
            }
        }

    }
}
// Обработка ошибок
fun handleAuthException(exception : Throwable?) : String {
    return when(exception) {
        is FirebaseAuthUserCollisionException -> "Пользователь с таким email уже существует"
        is FirebaseAuthWeakPasswordException -> "Пароль слишком слабый. Пожалуйста, используйте более надежный пароль."
        is FirebaseAuthInvalidCredentialsException -> "Неверный email или пароль. Пожалуйста, попробуйте еще раз."
        else -> "Произошла ошибка при регистрации. Пожалуйста, попробуйте еще раз."
    }
}




