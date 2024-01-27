package com.cofion.common.database.tables

import com.cofion.common.data.dtos.UserDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UsersTable: Table("users") {
    val id = varchar("id", 100)
    val firstName = varchar("first_name", 50).nullable()
    val lastName = varchar("last_name", 50).nullable()
    val email = varchar("email", 100)
    val password = varchar("password", 100)
    val createAccountDate = date("create_account_dt")
    val token = varchar("token", 100).nullable()

    override val primaryKey = PrimaryKey(id)

    fun getUserWithEmail(email: String): UserDTO? {
        val user = transaction{
            val user = UsersTable.selectAll().where {
                UsersTable.email eq email
            }.firstOrNull()

            if (user == null){
                return@transaction null
            }

            return@transaction UserDTO(
                id = user[UsersTable.id],
                firstName = user[firstName],
                lastName = user[lastName],
                createAccountDate = user[createAccountDate],
                email = user[UsersTable.email],
                password = user[password],
                token = user[token],
            )
        }

        return user
    }
}