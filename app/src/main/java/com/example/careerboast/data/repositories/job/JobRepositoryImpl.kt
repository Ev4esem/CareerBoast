package com.example.careerboast.data.repositories.job

import android.util.Log
import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.domain.model.jobs.Job
import com.example.careerboast.domain.model.jobs.JobDetail
import com.example.careerboast.domain.repositories.job.JobRepository
import com.example.careerboast.utils.FAVORITE_JOBS
import com.example.careerboast.utils.FAVORITE_MENTORS
import com.example.careerboast.utils.JOBS
import com.example.careerboast.utils.JOB_DETAIL
import com.google.firebase.firestore.DocumentSnapshot
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

        val jobs = db.collection(JOBS).document(jobId).collection(JOB_DETAIL).document(jobId)
        val documentSnapshot: DocumentSnapshot = jobs.get().await()
        val jobDetail = documentSnapshot.toObject<JobDetail>()
        jobDetail?.let { emit(it) }

    }.flowOn(ioDispatcher)



    override suspend fun setFavoriteJob(job : Job) : Flow<Boolean> = flow<Boolean> {

        when(job.favorite) {
            true -> {
                deleteJobDocument(job.id)
            }
            false -> {
                db.collection(FAVORITE_JOBS)
                    .add(job)
                    .addOnSuccessListener { documentReference ->
                        Log.d("FavoriteJob", "DocumentSnapshot written with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("FavoriteJob", "Error adding document", e)
                    }
            }

        }
        emit(!job.favorite)
    }.flowOn(ioDispatcher)

    private suspend fun deleteJobDocument(jobId : String) {
        db.collection(FAVORITE_JOBS).document(jobId)
            .delete()
            .addOnSuccessListener { Log.d("FavoriteJob", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w("FavoriteJob", "Error deleting document", e) }
    }

    override suspend fun getJobFavoriteList() : Flow<List<Job>> = flow {
        val jobs =  db.collection(FAVORITE_JOBS)
        val querySnapshot : QuerySnapshot = jobs.get().await()
        val job = querySnapshot.toObjects(Job::class.java)
        emit(job)
    }.flowOn(ioDispatcher)

}