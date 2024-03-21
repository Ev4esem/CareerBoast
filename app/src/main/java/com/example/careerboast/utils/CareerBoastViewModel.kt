package com.example.careerboast.utils

import androidx.compose.material3.Snackbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerboast.common.snackbar.SnackbarManager
import com.example.careerboast.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.example.careerboast.domain.repositories.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class CareerBoastViewModel @Inject constructor (
    private val logService : LogService
) : ViewModel() {

    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _ , throwable ->
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )

}