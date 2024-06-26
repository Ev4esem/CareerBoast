package com.example.careerboast.data.repositories.interview

import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.model.interviews.StudyMaterial
import com.example.careerboast.domain.repositories.interview.InterviewRepository
import com.example.careerboast.utils.STUDY_MATERIAL
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


class InterviewRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : InterviewRepository {

    override suspend fun getInterviewById(interviewId: String): Flow<List<Question>> = flow {
        emit(db.collection(interviewId).get().await().toObjects(Question::class.java))
    }.flowOn(ioDispatcher)

    override suspend fun getStudyMaterialById(studyMaterialId: String): Flow<StudyMaterial?> =
        flow {
            val questions = db.collection(STUDY_MATERIAL).document(studyMaterialId).get().await()
                .toObject<StudyMaterial>()
            questions?.let { emit(it) }
        }.flowOn(ioDispatcher)
}


