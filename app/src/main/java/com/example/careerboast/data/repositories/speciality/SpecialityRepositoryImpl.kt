package com.example.careerboast.data.repositories.speciality

import android.util.Log
import com.example.careerboast.di.IoDispatcher
import com.example.careerboast.domain.model.specialities.Speciality
import com.example.careerboast.domain.repositories.speciality.SpecialitiesRepository
import com.example.careerboast.utils.SPECIALITIES
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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
        val specialitiesCollection = db.collection(SPECIALITIES).get().await().toObjects(Speciality::class.java)
        emit(specialitiesCollection)
    }.flowOn(ioDispatcher)

}