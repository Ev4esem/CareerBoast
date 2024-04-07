package com.example.careerboast.data.repositories.job

import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobDetail
import com.example.careerboast.domain.model.jobs.JobEntity
import com.example.careerboast.domain.model.jobs.toFileEntity
import com.example.careerboast.domain.repositories.job.JobRepository
import com.example.careerboast.utils.FAVORITE_JOBS
import com.example.careerboast.utils.JOBS
import com.example.careerboast.utils.JOB_DETAIL
import com.example.careerboast.utils.MENTORS
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) : JobRepository {

    override suspend fun getJobList() : Flow<List<Job>> = flow {

        val jobs =  db.collection(JOBS)
        val querySnapshot : QuerySnapshot = jobs.get().await()
        val job = querySnapshot.toObjects(Job::class.java)
        emit(job)

    }.flowOn(ioDispatcher)

    override suspend fun getJobById(jobId : String) : Flow<JobDetail> = flow {

        val jobs = db
            .collection(JOBS)
            .document(jobId)
            .collection(JOB_DETAIL)
            .document(jobId)
        val documentSnapshot: DocumentSnapshot = jobs.get().await()
        val jobDetail = documentSnapshot.toObject<JobDetail>()
        jobDetail?.let { emit(it) }

    }.flowOn(ioDispatcher)



    override suspend fun setFavoriteJob(job : Job) : Flow<Boolean> = flow {

        when(job.favorite) {
            true -> {
                db.collection(JOBS).document(job.id)
                    .update(
                        "favorite" ,  false
                    )
                deleteJobDocument(job.id)
            }
            false -> {
                db.collection(FAVORITE_JOBS)
                    .add(job.toFileEntity(true))
                    .await()
                db.collection(JOBS).document(job.id)
                    .update(
                        "favorite" ,  true
                    )
            }
        }
        emit(!job.favorite)
    }.flowOn(ioDispatcher)

    private suspend fun deleteJobDocument(jobId : String) {
      var jobCollection =  db.collection(FAVORITE_JOBS)
          .whereEqualTo("id", jobId)
      jobCollection.get()
          .addOnSuccessListener { querySnapshot ->
              for (doc in querySnapshot) {
                  doc.reference.delete()
              }
          }.await()
    }

    override suspend fun getJobFavoriteList() : Flow<List<JobEntity>> = flow {
        val jobs =  db.collection(FAVORITE_JOBS)
        val querySnapshot : QuerySnapshot = jobs.get().await()
        val job = querySnapshot.toObjects(JobEntity::class.java)
        emit(job)
    }.flowOn(ioDispatcher)

}