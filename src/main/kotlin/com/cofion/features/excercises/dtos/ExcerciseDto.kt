package com.cofion.features.excercises.dtos

data class ExcerciseDto(
    val id: Int,
    val name: String,
    val type: String,
    val description: String?,
    val imageUrl: String?,
    val videoUrl: String?,
    val authorId: String?,
    val difficulty: String?
)
