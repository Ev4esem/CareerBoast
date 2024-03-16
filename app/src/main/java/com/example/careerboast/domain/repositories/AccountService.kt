package com.example.careerboast.domain.repositories

import com.example.careerboast.domain.model.login.UserAccount
import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUserId: String

    val hasUser: Boolean

    val currentUser: Flow<UserAccount>

    suspend fun register(email: String, password: String)


    suspend fun signOut()


}