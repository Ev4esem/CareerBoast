package com.example.careerboast.domain.use_cases.login

import com.example.careerboast.domain.repositories.AccountService
import javax.inject.Inject

class RegisterAccountUseCase @Inject constructor(
    private val accountService : AccountService
) {

    suspend operator fun invoke(email : String, password : String) {
        accountService.register(email,password)
    }

}