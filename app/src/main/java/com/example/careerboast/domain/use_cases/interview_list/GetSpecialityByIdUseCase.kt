package com.example.careerboast.domain.use_cases.interview_list

import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.domain.repositories.interviews.InterviewListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecialityByIdUseCase @Inject constructor(
    private val repo : InterviewListRepository
) {

    suspend operator fun invoke(specialityId : String) : Flow<List<Interview>> {
        return repo.getSpecialityById(specialityId)
    }

}