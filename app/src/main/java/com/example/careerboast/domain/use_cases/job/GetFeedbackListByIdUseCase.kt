package com.example.careerboast.domain.use_cases.job

import com.example.careerboast.domain.model.jobs.FeedbackJob
import com.example.careerboast.domain.repositories.job.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedbackListByIdUseCase @Inject constructor(
    private val repo : JobRepository
) {

    suspend operator fun invoke(jobDetailId : String) : Flow<List<FeedbackJob>> {
       return repo.getFeedbackListById(jobDetailId)
    }

}