package com.example.careerboast.domain.use_cases.login

import com.example.careerboast.domain.repositories.login.AccountService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
   private val accountService : AccountService
) {

    suspend operator fun invoke() {
        accountService.signOut()
    }

}
