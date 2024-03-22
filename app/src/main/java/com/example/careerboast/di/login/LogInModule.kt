package com.example.careerboast.di.login

import com.example.careerboast.data.repositories.login.AccountServiceImpl
import com.example.careerboast.domain.repositories.login.AccountService
import dagger.Binds
import dagger.Module
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