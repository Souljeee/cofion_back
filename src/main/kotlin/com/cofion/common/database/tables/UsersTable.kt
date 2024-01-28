package com.cofion.common.database.tables

import com.cofion.common.data.dtos.UserDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object UsersTable: Table("users") {
    val id = varchar("id", 100)
    val firstName = varchar("first_name", 50).nullable()
    val lastName = varchar("last_name", 50).nullable()
    val email = varchar("email", 100)
    val password = varchar("password", 100)
    val createAccountDate = date("create_account_dt")
    val token = varchar("token", 100).nullable()
    val confirmed = bool("confirmed")

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
            )
        }

        return user
    }

    fun saveUser(user: UserDTO){
        transaction{
            UsersTable.insert {
                it[id] = user.id
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[createAccountDate] = user.createAccountDate
                it[email] = user.email
                it[password] = user.password
                it[token] = null
                it[confirmed] = false
            }
        }
    }

    fun confirmUserWithEmail(email: String){
        transaction {
            UsersTable.update({UsersTable.email eq email}) {
                it[UsersTable.confirmed] = true
            }
        }
    }

    fun updateAuthToken(email: String): String{
        val token = transaction {
            val token = UUID.randomUUID().toString()

            UsersTable.update({UsersTable.email eq email}) {
                it[UsersTable.token] = token
            }

            return@transaction token
        }

        return token
    }
}