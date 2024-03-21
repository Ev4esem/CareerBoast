package com.example.careerboast.di.login

import com.example.careerboast.data.repositories.AccountServiceImpl
import com.example.careerboast.domain.repositories.AccountService
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LogInModule {



    @Binds
    fun accountServiceImplToAccountService(
        accountServiceImpl : AccountServiceImpl
    ) : AccountService

}