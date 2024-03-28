package com.example.careerboast.domain.use_cases.job

import com.example.careerboast.domain.model.jobs.JobDetail
import com.example.careerboast.domain.repositories.job.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJobByIdUseCase @Inject constructor(
    private val repo : JobRepository
) {

    suspend operator fun invoke(jobId : String) : Flow<JobDetail> {
        return repo.getJobById(jobId)
    }

}