package com.example.careerboast.di.mentor

import com.example.careerboast.data.repositories.mentor.MentorRepositoryImpl
import com.example.careerboast.domain.repositories.mentors.MentorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MentorModule {


    @Binds
    fun mentorRepositoryImpl_to_MentorRepository(
        mentorRepositoryImpl : MentorRepositoryImpl
    ) : MentorRepository

}