package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object CoachInfoTable: Table("coach_info") {
    val id = varchar("id", 100)

    override val primaryKey = PrimaryKey(id)

    fun createCoachAccount(): String{
        val id = transaction{
            val uuid = UUID.randomUUID().toString()

            CoachInfoTable.insert {
                it[id] = uuid
            }

            return@transaction uuid
        }

        return id
    }
}