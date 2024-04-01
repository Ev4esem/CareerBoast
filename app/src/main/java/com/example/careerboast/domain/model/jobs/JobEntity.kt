package com.example.careerboast.domain.model.jobs

data class JobEntity(
    val id : String = "",
    val logoCompany : String = "",
    val status : String = "",
    val subTitle : String = "",
    val title : String = "",
    val favorite : Boolean = false
)

fun JobEntity.toEntity() = Job(
    id = id,
    logoCompany = logoCompany,
    status = status,
    subTitle = subTitle,
    title = title,
    favorite = favorite
)

fun Job.toFileEntity(favorite : Boolean) = Job(
    id = id,
    logoCompany = logoCompany,
    status = status,
    subTitle = subTitle,
    title = title,
    favorite = favorite
)

fun List<JobEntity>.toListEntity() : List<Job> {
    return map { it.toEntity() }
}
