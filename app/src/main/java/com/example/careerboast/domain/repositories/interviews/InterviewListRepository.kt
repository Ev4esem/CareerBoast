package com.example.careerboast.domain.repositories.interviews

import com.example.careerboast.domain.model.interviews.Interview
import kotlinx.coroutines.flow.Flow

interface InterviewListRepository {


    suspend fun getSpecialityById(specialityId : String) : Flow<List<Interview>>


}