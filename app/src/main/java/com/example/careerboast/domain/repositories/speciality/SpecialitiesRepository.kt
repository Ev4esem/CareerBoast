package com.example.careerboast.domain.repositories.speciality

import com.example.careerboast.domain.model.specialities.Speciality
import kotlinx.coroutines.flow.Flow

interface SpecialitiesRepository {

    suspend fun getSpecialitiesList() : Flow<List<Speciality>>

    suspend fun getSpecialityById(specialityId : String) : Flow<Speciality>



}