package com.example.careerboast.common

import com.example.careerboast.R
import com.example.careerboast.view.screens.job.InternshipJob
import com.example.careerboast.view.screens.mentor.InternshipMentor


enum class TabItemJob(
    val title : Int,
    val screen : InternshipJob
) {

    Job(
        title = R.string.jobs,

        screen = InternshipJob.Jobs
    ),
    FavoriteJob(
        title = R.string.favorite,
        screen = InternshipJob.Favorite
    )

}
enum class TabItemMentor(
    val title : Int,
    val screen : InternshipMentor
) {

    Mentor(
        title = R.string.mentors,

        screen = InternshipMentor.Mentors
    ),
    FavoriteMentor(
        title = R.string.favorite,
        screen = InternshipMentor.Favorite
    )

}
