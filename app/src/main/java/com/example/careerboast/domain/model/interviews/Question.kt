package com.example.careerboast.domain.model.interviews

data class Question(
    val id: String = "",
    val text: String = "",
    val answers: List<Answer> = listOf(),
    val correctAnswerIndex: Int = 0,
){
    companion object {
        val questions = listOf(
            Question(
                id = "1",
                text = "Что такое Activity в Android?",
                answers = listOf(
                    Answer(id = 1, title = "Утилита для асинхронной работы"),
                    Answer(id = 2, title = "Компонент пользовательского интерфейса приложения"),
                    Answer(id = 3, title = "Сервис для выполнения фоновых операций"),
                    Answer(id = 4, title = "Файл ресурсов приложения")
                ),
                correctAnswerIndex = 2
            ),
            Question(
                id = "2",
                text = "Какой компонент отвечает за выполнение долгосрочных операций в фоне?",
                answers = listOf(
                    Answer(id = 1, title = "Service"),
                    Answer(id = 2, title = "BroadcastReceiver"),
                    Answer(id = 3, title = "ContentProvider"),
                    Answer(id = 4, title = "Activity")
                ),
                correctAnswerIndex = 1
            ),
            Question(
                id = "3",
                text = "Какой метод жизненного цикла Activity вызывается при пересоздании активности, например, при изменении конфигурации?",
                answers = listOf(
                    Answer(id = 1, title = "onPause()"),
                    Answer(id = 2, title = "onResume()"),
                    Answer(id = 3, title = "onRestart()"),
                    Answer(id = 4, title = "onCreate()")
                ),
                correctAnswerIndex = 4
            ),
            Question(
                id = "4",
                text = "Какой инструмент используется для управления зависимостями в проектах Android?",
                answers = listOf(
                    Answer(id = 1, title = "Gradle"),
                    Answer(id = 2, title = "Maven"),
                    Answer(id = 3, title = "Ant"),
                    Answer(id = 4, title = "Kotlin DSL")
                ),
                correctAnswerIndex = 1
            ),
            Question(
                id = "5",
                text = "Какое утверждение о Fragments в Android верно?",
                answers = listOf(
                    Answer(id = 1, title = "Fragments не могут содержать другие Fragments"),
                    Answer(id = 2, title = "Fragments используются только в активностях"),
                    Answer(id = 3, title = "Fragments предназначены для модульного представления пользовательского интерфейса"),
                    Answer(id = 4, title = "Fragments устарели и не рекомендуются к использованию")
                ),
                correctAnswerIndex = 3
            )
        )

    }
}
