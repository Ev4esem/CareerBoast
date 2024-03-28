package com.example.careerboast.domain.use_cases.job

import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.repositories.job.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJobFavoriteListUseCase @Inject constructor(
    private val repo : JobRepository
) {

    suspend operator fun invoke() : Flow<List<Job>> {
        return repo.getJobFavoriteList()
    }

}