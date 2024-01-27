package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object UsersTable: Table("users") {
    val id = varchar("id", 100)
    val firstName = varchar("first_name", 50).nullable()
    val lastName = varchar("last_name", 50).nullable()
    val login = varchar("login", 50)
    val password = varchar("password", 100)
    val createAccountDate = date("create_account_dt")
    val token = varchar("token", 100).nullable()
}