package com.example.careerboast.domain.use_cases.speciality

import com.example.careerboast.domain.model.specialities.Speciality
import com.example.careerboast.domain.repositories.SpecialitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecialityListUseCase @Inject constructor(
    private val specialitiesRepository : SpecialitiesRepository
) {

    suspend operator fun invoke() : Flow<List<Speciality>> {
       return specialitiesRepository.getSpecialitiesList()
    }

}