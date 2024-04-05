package com.example.careerboast.domain.repositories.mentors

import com.example.careerboast.domain.model.mentors.Mentor
import com.example.careerboast.domain.model.mentors.MentorDetail
import com.example.careerboast.domain.model.mentors.MentorEntity
import kotlinx.coroutines.flow.Flow

interface MentorRepository {

    suspend fun getMentors() : Flow<List<Mentor>>

    suspend fun getFavoriteMentors() : Flow<List<MentorEntity>>

    suspend fun setFavoriteMentor(mentor : Mentor) : Flow<Boolean>

    suspend fun getMentorById(
        mentorId : String
    ) : Flow<MentorDetail>

}