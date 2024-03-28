package com.example.careerboast.domain.use_cases.job

import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.repositories.job.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavoriteJobUseCase @Inject constructor(
    private val repo : JobRepository
) {

    suspend operator fun invoke(job : Job) : Flow<Boolean> {
        return repo.setFavoriteJob(job)
    }

}