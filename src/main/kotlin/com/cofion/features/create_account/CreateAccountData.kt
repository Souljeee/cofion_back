package com.cofion.features.create_account

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountPayload(
    val email: String,
    val password: String,
)

@Serializable
data class CreateAccountResponse(
    val accessAllowed: Boolean,
    val message: String
)

@Serializable
data class CreateCodePayload(
    val email: String
)

@Serializable
data class CreateCodeResponse(
    val codeSent: Boolean
)

@Serializable
data class ConfirmCodePayload(
    val email: String,
    val code: String
)

@Serializable
data class ConfirmCodeResponse(
    val confirmationStatus: UserConfirmationStatus,
)