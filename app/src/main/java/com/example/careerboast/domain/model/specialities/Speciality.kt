package com.example.careerboast.domain.model.specialities

import com.example.careerboast.domain.model.interviews.Interview
import com.google.errorprone.annotations.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Speciality(
    val id : String = "",
    val imageUrl : String = "",
    val title : String = "",
    val interviews : List<Interview> = listOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Speciality

        if (id != other.id) return false
        if (imageUrl != other.imageUrl) return false
        if (title != other.title) return false
        if (interviews != other.interviews) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + interviews.hashCode()
        return result
    }
}
