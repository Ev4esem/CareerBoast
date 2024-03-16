package com.example.careerboast.domain.repositories

interface SpecialitiesRepository {

    fun getSpecialitiesList() : List<String>

    fun getSpecialityById(speciality : Int) : String



}