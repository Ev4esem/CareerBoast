package com.example.careerboast.domain.use_cases.login

import com.example.careerboast.domain.repositories.login.AccountService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repo : AccountService
) {

    suspend operator fun invoke(email : String, password : String) : Flow<AuthResult> {
        return repo.signIn(email, password)
    }

}