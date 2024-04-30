package com.cofion.features.user.user

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val createAccountDate: LocalDate,
    val email: String,
    val accountType: String,
)