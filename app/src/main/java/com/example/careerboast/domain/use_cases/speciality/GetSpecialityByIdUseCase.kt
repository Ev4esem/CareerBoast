package com.example.careerboast.domain.use_cases.speciality

import com.example.careerboast.domain.model.specialities.Speciality
import com.example.careerboast.domain.repositories.SpecialitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecialityByIdUseCase @Inject constructor(
    private val specialitiesRepository : SpecialitiesRepository
) {

    suspend operator fun invoke(specialityId : String) : Flow<Speciality> {
        return specialitiesRepository.getSpecialityById(specialityId)
    }

}