package com.example.careerboast.domain.use_cases.login

import com.example.careerboast.domain.repositories.login.AccountService
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterAccountUseCase @Inject constructor(
    private val accountService : AccountService
) {

    suspend operator fun invoke(email : String, password : String) : Flow<AuthResult>  {
       return accountService.register(email,password)
    }

}