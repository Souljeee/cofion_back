package com.cofion.common.data.dtos

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update


object SentCodesTable : Table("sent_codes") {
    val email = varchar("email", 100)
    val code = varchar("code", 6)

    override val primaryKey = PrimaryKey(email)

    fun setCode(email: String, code: String){
        transaction {
            val existingCode = SentCodesTable.selectAll().where { SentCodesTable.email eq email }.singleOrNull()

            if(existingCode != null){
                SentCodesTable.update({SentCodesTable.email eq email}){
                    it[SentCodesTable.code] = code
                }

                return@transaction
            }

            SentCodesTable.insert {
                it[SentCodesTable.email] = email
                it[SentCodesTable.code] = code
            }
        }
    }
}