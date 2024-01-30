package com.cofion.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthPayload(
    val email: String,
    val password: String,
    val accountType: String,
)

@Serializable
data class AuthResponse(
    val authStatus: AuthStatus,
    val token: String?,
)

@Serializable
data class LogoutPayload(
    val email: String
)

@Serializable
data class CheckAuthResponse(
    val hasAccess: Boolean
)

enum class AuthStatus {
    SUCCESS, NONEXISTENT_USER, WRONG_PASSWORD
}

data class AuthDTO(
    val authStatus: AuthStatus,
    val token: String?,
)