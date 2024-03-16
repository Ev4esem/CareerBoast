package com.example.careerboast.domain.repositories

import com.example.careerboast.domain.model.interviews.Question

interface InterviewsRepository {

   suspend fun getInterviewsList() : List<Question>

}