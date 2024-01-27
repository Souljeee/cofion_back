package com.cofion.common.data.dtos

import java.util.*


class UserDTO(
    val id: String,
    val firstName: String,
    val lastName: String,
    val createAccountDate: Date,
    val login: String,
    val password: String,
    val token: String,
)