package com.example.careerboast.domain.use_cases.mentor

import com.example.careerboast.domain.model.mentors.Mentor
import com.example.careerboast.domain.repositories.mentors.MentorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMentorsListUseCase @Inject constructor(
    private val repo : MentorRepository
) {

    suspend operator fun invoke() : Flow<List<Mentor>> {
        return repo.getMentors()
    }

}