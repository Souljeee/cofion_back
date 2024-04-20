package com.cofion.features.workout_plans

import kotlinx.serialization.Serializable

@Serializable
data class ApproachPayload(
    val orderNumber: Int,
    val repsCount: Int,
    val weight: Double,
    val rest: Int,
)

@Serializable
data class ExercisePayload(
    val exerciseId: Int,
    val comment: String?,
    val orderNumber: Int,
    val approach: List<ApproachPayload>,
)

@Serializable
data class SessionPayload(
    val orderNumber: Int,
    val type: String,
    val duration: Int,
    val exercises: List<ExercisePayload>,
)

@Serializable
data class WorkoutPlanPayload(
    val title: String,
    val description: String,
    val difficulty: String,
    val authorId: String,
    val type: String,
    val sessionPerWeek: Int,
    val planDuration: Int,
    val sessions: List<SessionPayload>,
)