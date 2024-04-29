package com.cofion.features.user

import com.cofion.common.data.dtos.UserDTO
import com.cofion.common.database.tables.UsersTable

class UserController {
    fun getUserInfo(authToken: String): UserDTO?{
        val user = UsersTable.getUserByAuthToken(authToken = authToken)

        return user
    }
}