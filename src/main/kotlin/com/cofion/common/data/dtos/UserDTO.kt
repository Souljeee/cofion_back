package com.cofion.common.data.dtos

import java.time.LocalDate


class UserDTO(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val createAccountDate: LocalDate,
    val email: String,
    val password: String,
    val token: String?,
)