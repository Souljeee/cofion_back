package com.cofion.features.create_account

import com.cofion.common.database.tables.UsersTable

class CreateAccountController {
    fun checkAccessForCreatingAccount(email: String): Boolean {
        val user = UsersTable.getUserWithEmail(email = email)

        return user == null
    }
}