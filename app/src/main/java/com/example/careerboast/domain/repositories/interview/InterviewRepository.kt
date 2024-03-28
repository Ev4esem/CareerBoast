package com.example.careerboast.domain.repositories.interview

import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.domain.model.interviews.Question
import kotlinx.coroutines.flow.Flow

interface InterviewRepository {

   suspend fun getInterviewById(
      interviewId : String
   ) : Flow<List<Question>>

}