package com.example.careerboast.domain.use_cases.mentor

import com.example.careerboast.domain.model.mentors.MentorEntity
import com.example.careerboast.domain.repositories.mentors.MentorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMentorListUseCase @Inject constructor(
    private val repo : MentorRepository
) {

    suspend operator fun invoke() : Flow<List<MentorEntity>> {
        return repo.getFavoriteMentors()
    }

}