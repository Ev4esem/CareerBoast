package com.example.careerboast.data.repositories.mentor

import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.mentors.Mentor
import com.example.careerboast.domain.model.mentors.MentorDetail
import com.example.careerboast.domain.model.mentors.MentorEntity
import com.example.careerboast.domain.model.mentors.toMentorEntity
import com.example.careerboast.domain.repositories.mentors.MentorRepository
import com.example.careerboast.utils.FAVORITE_MENTORS
import com.example.careerboast.utils.MENTORS
import com.example.careerboast.utils.MENTOR_DETAIL
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MentorRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) : MentorRepository {

    override suspend fun getMentors() : Flow<List<Mentor>> = flow {
        val mentors =  db.collection(MENTORS).get().await().toObjects(Mentor::class.java)
        emit(mentors)
    }.flowOn(ioDispatcher)

    override suspend fun getFavoriteMentors() : Flow<List<MentorEntity>> = flow {
        val mentorsCollection = db.collection(FAVORITE_MENTORS).get().await().toObjects(MentorEntity::class.java)
        emit(mentorsCollection)
    }.flowOn(ioDispatcher)


    override suspend fun setFavoriteMentor(mentor : Mentor) : Flow<Boolean> = flow {
        when(mentor.favorite) {
            true -> {
                deleteJobDocument(mentor.id)
                db.collection(MENTORS).document(mentor.id).update("favorite" ,  false)
            }
            false -> {
                db.collection(FAVORITE_MENTORS).add(mentor.toMentorEntity(true)).await()
                db.collection(MENTORS).document(mentor.id).update("favorite" ,  true)
            }
        }
        emit(!mentor.favorite)
    }.flowOn(ioDispatcher)

    private suspend fun deleteJobDocument(mentorId : String) {
        db.collection(FAVORITE_MENTORS)
            .whereEqualTo("id", mentorId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (doc in querySnapshot) {
                    doc.reference.delete()
                }
            }.await()
    }

    override suspend fun getMentorById(mentorId : String) : Flow<MentorDetail> = flow {
        val mentorDetail = db.collection(MENTORS).document(mentorId).collection(MENTOR_DETAIL)
            .document(mentorId).get().await().toObject<MentorDetail>()
        mentorDetail?.let { emit(it) }
    }.flowOn(ioDispatcher)

}