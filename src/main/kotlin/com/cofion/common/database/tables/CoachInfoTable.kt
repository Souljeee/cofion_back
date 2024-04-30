package com.cofion.common.database.tables

import com.cofion.features.user.coach_info.CoachInfoDto
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object CoachInfoTable: Table("coach_info") {
    val id = varchar("id", 100)
    val experience = integer("experience").nullable()
    val description = text("description").nullable()
    val rating = double("rating").default(0.0)

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

    fun getCoachInfo(id: String): CoachInfoDto{
        val coachDto = transaction {
            val coach = CoachInfoTable.selectAll().where {
                CoachInfoTable.id eq id
            }.single()

            return@transaction CoachInfoDto(
                coachId = coach[CoachInfoTable.id],
                experience = coach[CoachInfoTable.experience],
                description = coach[CoachInfoTable.description],
                rating = coach[CoachInfoTable.rating],
            )
        }

        return coachDto
    }
}