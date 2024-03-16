package com.example.careerboast.data.repositories

import com.example.careerboast.domain.model.interviews.Question
import com.example.careerboast.domain.repositories.InterviewsRepository

class InterviewRepositoryImpl() : InterviewsRepository {

    override suspend fun getInterviewsList() : List<Question> {

        val questions = listOf(
            Question(
                id = 1,
                text = "Что такое Fragment и когда он используется в Android-разработке?",
                answers =  listOf(
                    "Fragment - это один из главных компонентов Android",
                    "Fragment - это наследник View",
                    "Fragment - это вспомогательный компанент , который создали чтоб распологать несколько фрагментов на одном экране"
                ),
                correctAnswerIndex = 3,
                studyMaterials = listOf()
            ),
            Question(
                id = 1,
                text = "Что такое Fragment и когда он используется в Android-разработке?",
                answers =  listOf(
                    "Fragment - это один из главных компонентов Android",
                    "Fragment - это наследник View",
                    "Fragment - это вспомогательный компанент , который создали чтоб распологать несколько фрагментов на одном экране"
                ),
                correctAnswerIndex = 3,
                studyMaterials = listOf()
            ),
            Question(
                id = 1,
                text = "Что такое Fragment и когда он используется в Android-разработке?",
                answers =  listOf(
                    "Fragment - это один из главных компонентов Android",
                    "Fragment - это наследник View",
                    "Fragment - это вспомогательный компанент , который создали чтоб распологать несколько фрагментов на одном экране"
                ),
                correctAnswerIndex = 3,
                studyMaterials = listOf()
            ),
            Question(
                id = 1,
                text = "Что такое Fragment и когда он используется в Android-разработке?",
                answers =  listOf(
                    "Fragment - это один из главных компонентов Android",
                    "Fragment - это наследник View",
                    "Fragment - это вспомогательный компанент , который создали чтоб распологать несколько фрагментов на одном экране"
                ),
                correctAnswerIndex = 3,
                studyMaterials = listOf()
            )

        )
        return questions
    }

}