package com.example.careerboast.domain.repositories.interview

import com.example.careerboast.domain.model.interviews.Answer
import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.model.interviews.StudyMaterial
import kotlinx.coroutines.flow.Flow

interface InterviewRepository {

   suspend fun getInterviewById(
      interviewId : String
   ) : Flow<List<Question>>


   suspend fun getStudyMaterialById(
      studyMaterialId : String
   ) : Flow<StudyMaterial?>

}