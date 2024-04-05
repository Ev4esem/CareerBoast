package com.example.careerboast.domain.use_cases.mentor

import com.example.careerboast.domain.model.mentors.MentorDetail
import com.example.careerboast.domain.repositories.mentors.MentorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMentorByIdUseCase @Inject constructor(
    private val repo : MentorRepository
) {

    suspend operator fun invoke(mentorId : String) : Flow<MentorDetail> {
        return repo.getMentorById(mentorId)
    }

}