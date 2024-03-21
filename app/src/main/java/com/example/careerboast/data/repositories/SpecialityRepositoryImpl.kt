package com.example.careerboast.data.repositories

import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.specialities.Speciality
import com.example.careerboast.domain.repositories.SpecialitiesRepository
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

class SpecialityRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) : SpecialitiesRepository {

    override suspend fun getSpecialitiesList() : Flow<List<Speciality>> = flow {
        val specialitiesCollection = db.collection(SPECIALITIES)
        val querySnapshot : QuerySnapshot = specialitiesCollection.get().await()
        val specialities = querySnapshot.toObjects(Speciality::class.java)
        emit(specialities)
    }.flowOn(ioDispatcher)

    // todo
    override suspend fun getSpecialityById(specialityId : String) : Flow<Speciality> = flow {

        val specialityDocument = db.collection(SPECIALITIES).document(specialityId)
        val documentSnapshot: DocumentSnapshot = specialityDocument.get().await()
        val speciality = documentSnapshot.toObject<Speciality>()
        speciality?.let { emit(it) }
    }.flowOn(ioDispatcher)


}