package com.example.careerboast.domain.repositories.login

import com.example.careerboast.domain.model.login.UserAccount
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUserId: String

    val hasUser: Boolean

    val currentUser: Flow<UserAccount>

    suspend fun register(email: String, password: String) : Flow<AuthResult>


    suspend fun signOut()


}