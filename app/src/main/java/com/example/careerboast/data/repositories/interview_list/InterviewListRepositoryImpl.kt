package com.example.careerboast.data.repositories.interview_list

import android.util.Log
import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.interviews.Interview
import com.example.careerboast.domain.repositories.interviews.InterviewListRepository
import com.example.careerboast.utils.INTERVIEWS
import com.example.careerboast.utils.SPECIALITIES
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

class InterviewListRepositoryImpl @Inject constructor (
    private val db : FirebaseFirestore,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) : InterviewListRepository {


    override suspend fun getInterviewList() : Flow<List<Interview>> = flow {
        val interviewsCollection = db.collection(INTERVIEWS)
        val querySnapshot : QuerySnapshot = interviewsCollection.get().await()
        val interviews = querySnapshot.toObjects(Interview::class.java)
        emit(interviews)
    }.flowOn(ioDispatcher)




    override suspend fun getSpecialityById(specialityId : String) : Flow<List<Interview>> = flow {

        val specialityDocument = db.collection(SPECIALITIES)
            .document(specialityId)
            .collection(INTERVIEWS)

        val documentSnapshot: QuerySnapshot = specialityDocument.get().await()

        val speciality = documentSnapshot.toObjects(Interview::class.java)
        Log.d("SpecialityId", "$speciality")
        emit(speciality)
    }.flowOn(ioDispatcher)

}