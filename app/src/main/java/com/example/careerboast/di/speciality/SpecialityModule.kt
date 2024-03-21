package com.example.careerboast.di.speciality

import com.example.careerboast.data.repositories.SpecialityRepositoryImpl
import com.example.careerboast.domain.repositories.SpecialitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface SpecialityModule {

    @Binds
    fun specialitiesRepositoryImpl_to_specialitiesRepository(
        specialitiesRepositoryImpl : SpecialityRepositoryImpl
    ) : SpecialitiesRepository

}