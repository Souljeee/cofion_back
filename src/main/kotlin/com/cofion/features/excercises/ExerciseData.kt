package com.cofion.features.excercises

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResponse(
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

@Serializable
data class MuscleGroupsResponse(
    val muscleGroups: List<String>
)