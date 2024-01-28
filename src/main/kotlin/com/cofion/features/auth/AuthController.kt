package com.cofion.features.auth

import com.cofion.common.database.tables.UsersTable

class AuthController {
    fun authUser(email: String, password: String): AuthDTO {
        val user = UsersTable.getUserWithEmail(email = email) ?: return AuthDTO(
            authStatus = AuthStatus.NONEXISTENT_USER,
            token = null
        )

        if(!user.confirmed){
            return AuthDTO(
                authStatus = AuthStatus.NONEXISTENT_USER,
                token = null
            )
        }

        if (user.password == password) {
            val token = UsersTable.updateAuthToken(email = email)

            return AuthDTO(
                authStatus = AuthStatus.SUCCESS,
                token = token
            )
        }

        return AuthDTO(
            authStatus = AuthStatus.WRONG_PASSWORD,
            token = null
        )
    }
}