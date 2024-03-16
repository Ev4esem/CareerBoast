package com.example.careerboast.domain.use_cases

import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.repositories.InterviewsRepository

class GetInterviewsListUseCase(val repository : InterviewsRepository) {

    suspend operator fun invoke() : List<Question> {
       return repository.getInterviewsList()
    }

}