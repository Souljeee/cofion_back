package com.cofion.features.user.coach_info

import kotlinx.serialization.Serializable

data class CoachInfoDto(
    val coachId: String,
    val experience: Int?,
    val description: String?,
    val rating: Double,
)

@Serializable
data class CoachInfoResponse(
    val coachId: String,
    val experience: Int?,
    val description: String?,
    val rating: Double,
)