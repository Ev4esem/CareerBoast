package com.example.careerboast.data.repositories

import com.example.careerboast.domain.model.login.UserAccount
import com.example.careerboast.domain.repositories.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor (
    private val auth : FirebaseAuth
) : AccountService {

    override val currentUserId : String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser : Boolean
        get() = auth.currentUser != null

    override val currentUser : Flow<UserAccount>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->

                val currentUser = auth.currentUser
                val userData = if (currentUser != null) {
                    UserAccount(
                        currentUser.uid, currentUser.email ?: ""
                    )
                } else {
                    UserAccount("", "")
                }
                this.trySend(userData)
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun register(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email,password).await()
    }


    override suspend fun signOut() {
        auth.signOut()
    }

}