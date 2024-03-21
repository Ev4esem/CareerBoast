package com.example.careerboast.di

import com.example.careerboast.data.repositories.LogServiceImpl
import com.example.careerboast.domain.repositories.LogService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun logServiceImplToLogService(
        logService : LogServiceImpl
    ) : LogService {
        return logService
    }

     @Provides
     fun provideFirebaseStore() : FirebaseFirestore = FirebaseFirestore.getInstance()



}