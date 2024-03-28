package com.example.careerboast.data.repositories.interview

import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.repositories.interview.InterviewRepository
import com.example.careerboast.utils.INTERVIEW
import com.example.careerboast.utils.INTERVIEWS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class InterviewRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore
) : InterviewRepository {


    override suspend fun getInterviewById(interviewId : String) : Flow<List<Question>> = flow{

        val interviews = db.collection(INTERVIEWS)
            .document(interviewId)
            .collection(INTERVIEW)

        val documentSnapshot: QuerySnapshot = interviews.get().await()

        val interview = documentSnapshot.toObjects(Question::class.java)

        emit(interview)

    }


}