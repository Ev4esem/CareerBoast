package com.example.careerboast.di.interview

import com.example.careerboast.data.repositories.interview_list.InterviewListRepositoryImpl
import com.example.careerboast.domain.repositories.interviews.InterviewListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InterviewModule {

    @Binds
    fun interviewListRepositoryImpl_to_InterviewListPepository(
        interviewListRepositoryImpl : InterviewListRepositoryImpl
    ) : InterviewListRepository

}