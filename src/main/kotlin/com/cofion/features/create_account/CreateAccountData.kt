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