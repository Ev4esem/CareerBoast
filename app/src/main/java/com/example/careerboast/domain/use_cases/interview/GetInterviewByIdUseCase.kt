package com.example.careerboast.domain.use_cases.interview

import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.repositories.interview.InterviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInterviewByIdUseCase @Inject constructor(
    private val repository : InterviewRepository
) {

    suspend operator fun invoke(interviewId : String) : Flow<List<Question>> {
        return repository.getInterviewById(interviewId)
    }

}