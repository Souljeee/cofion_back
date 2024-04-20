package com.cofion.features.excercises

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseReponse(
    val id: Int,
    val name: String,
    val type: String,
    val description: String?,
    val imageUrl: String?,
    val videoUrl: String?,
    val authorId: String?,
    val difficulty: String?,
    val muscleGroups: List<String>,
)