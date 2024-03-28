package com.example.careerboast.di.job

import com.example.careerboast.data.repositories.job.JobRepositoryImpl
import com.example.careerboast.domain.repositories.job.JobRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface JobModule {

    @Binds
    fun jobRepositoryImpl_to_JobRepository(
        jobRepositoryImpl : JobRepositoryImpl
    ) : JobRepository



}