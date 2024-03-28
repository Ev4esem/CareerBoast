package com.example.careerboast.di.interview

import com.example.careerboast.data.repositories.interview.InterviewRepositoryImpl
import com.example.careerboast.domain.repositories.interview.InterviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InterviewModule {

    @Binds
    fun interviewRepositoryImpl_to_InterviewRepository(
        interviewRepositoryImpl : InterviewRepositoryImpl
    ) : InterviewRepository

}