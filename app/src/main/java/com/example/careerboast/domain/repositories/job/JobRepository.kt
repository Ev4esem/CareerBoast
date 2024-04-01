package com.example.careerboast.domain.repositories.job

import com.example.careerboast.domain.model.jobs.FeedbackJob
import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobDetail
import com.example.careerboast.domain.model.jobs.JobEntity
import kotlinx.coroutines.flow.Flow

interface JobRepository {

   suspend fun getJobList() : Flow<List<Job>>

   suspend fun getJobById(jobId : String) : Flow<JobDetail>

   suspend fun setFavoriteJob(job : Job) : Flow<Boolean>

   suspend fun getJobFavoriteList() : Flow<List<Job>>

   suspend fun getFeedbackListById(
      jobId : String
   ) : Flow<List<FeedbackJob>>


}