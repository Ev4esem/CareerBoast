package com.example.careerboast.domain.use_cases.interview

import com.example.careerboast.domain.model.interviews.StudyMaterial
import com.example.careerboast.domain.repositories.interview.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudyMaterialByIdUseCase @Inject constructor(
    private val repository : InterviewRepository
) {

    suspend operator fun invoke(questionId : String) : Flow<StudyMaterial?> {
        return repository.getStudyMaterialById(questionId)
    }

}