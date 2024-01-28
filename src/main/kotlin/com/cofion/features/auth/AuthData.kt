package com.cofion.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthPayload(
    val email: String,
    val password: String,
)

@Serializable
data class AuthResponse(
    val authStatus: AuthStatus,
    val token: String?,
)

enum class AuthStatus{
    SUCCESS, NONEXISTENT_USER, WRONG_PASSWORD
}

data class AuthDTO(
    val authStatus: AuthStatus,
    val token: String?,
)