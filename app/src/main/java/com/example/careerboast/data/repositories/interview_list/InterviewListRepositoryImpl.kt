package com.example.careerboast.data.repositories.interview_list

import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.domain.repositories.interviews.InterviewListRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InterviewListRepositoryImpl @Inject constructor (
    private val db : FirebaseFirestore,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) : InterviewListRepository {
    override suspend fun getSpecialityById(specialityId : String) : Flow<List<Interview>> = flow {
        emit(db.collection(specialityId).get().await().toObjects(Interview::class.java))
    }.flowOn(ioDispatcher)

}