package com.cofion.features.user.coach_info

import kotlinx.serialization.Serializable

data class CoachInfoDto(
    val coachId: String,
    val experience: Int?,
    val description: String?,
    val rating: Double,
)

data class CoachRateDto(
    val rate: Int,
    val positiveComment: String,
    val negativeComment: String,
)

@Serializable
data class CoachInfoResponse(
    val coachId: String,
    val experience: Int?,
    val description: String?,
    val rating: Double,
)

@Serializable
data class CoachRateResponse(
    val rate: Int,
    val positiveComment: String,
    val negativeComment: String,
)