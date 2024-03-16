package com.example.careerboast.domain.model.interviews

data class Question(
    val id: Int,
    val text: String,
    val answers: List<String>,
    val correctAnswerIndex: Int,
    val studyMaterials: List<StudyMaterial>
)
