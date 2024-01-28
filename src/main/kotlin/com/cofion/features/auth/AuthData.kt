package com.cofion.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthPayload(
    val email: String,
    val password: String,
)