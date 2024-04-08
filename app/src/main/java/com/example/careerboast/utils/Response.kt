package com.example.careerboast.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import java.io.IOException

sealed class Response<out T> {

    object Loading: Response<Nothing>()

    data class Success<out T>(
        val data: T
    ): Response<T>()

    data class Failure(
        val e: Exception,
        val message : String?
    ): Response<Nothing>()

}

// Автоматическое обновление трех states
fun <T> Flow<T>.asResult() : Flow<Response<T>> {
    return this
        .map<T, Response<T>> { Response.Success(it) }
        .onStart { emit(Response.Loading) }
        .catch { ex ->
            val exception = if (ex is Exception) {
                ex // Если ex уже является Exception, используйте его
            } else {
                Exception(ex) // Преобразуйте Throwable в Exception
            }
            emit(
                Response.Failure(
                    e = exception,
                    message = handleException(exception)
                )
            )
        }
}

suspend fun <T> Flow<T>.collectAsResult(
    onSuccess : suspend (T) -> Unit = {},
    onError : suspend (exception : Throwable?, message : String?) -> Unit = {_ ,_ ->},
    onLoading : () -> Unit = {}
) {
    asResult().collect { result ->

        when(result) {
            is Response.Success -> onSuccess(result.data)
            is Response.Failure -> {
                val httpsError = handleException(result.e)
                onError(result.e, httpsError)
            }

            Response.Loading -> onLoading()

        }

    }
}


@Composable
fun <T> ObserveEffect(flow : Flow<T>, onEvent : (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}

// Обработка ошибок
fun handleException(exception : Exception?) : String {
    return when (exception) {
        is FirebaseFirestoreException -> parseFirestoreException(exception)
        is IOException -> "Произошла ошибка при загрузке данных, проверьте подключение к сети"
        is SecurityException -> "Проблема с безопасностью"
        else -> "Неизвестная ошибка: ${exception?.localizedMessage}"
    }
}

private fun parseFirestoreException(exception : FirebaseFirestoreException) : String {

    return when (exception.code) {
        FirebaseFirestoreException.Code.PERMISSION_DENIED -> "Доступ запрещен: ${exception.message}"
        FirebaseFirestoreException.Code.NOT_FOUND -> "Ресурс не найден: ${exception.message}"
        FirebaseFirestoreException.Code.ALREADY_EXISTS -> "Ресурс уже существует: ${exception.message}"
        FirebaseFirestoreException.Code.ABORTED -> "Операция отменена: ${exception.message}"
        FirebaseFirestoreException.Code.UNAUTHENTICATED -> "Не аутентифицирован: ${exception.message}"
        FirebaseFirestoreException.Code.UNAVAILABLE -> "Сервис недоступен: ${exception.message}"
        FirebaseFirestoreException.Code.CANCELLED -> "Операция отменена: ${exception.message}"
        else -> "Ошибка : ${exception.message}"
    }
}