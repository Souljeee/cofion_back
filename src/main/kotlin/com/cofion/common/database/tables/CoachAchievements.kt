package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object CoachAchievements: IntIdTable("coach_achievements") {
    val name = varchar("name", 500)
    val coachId = varchar("coach_id", 100).references(CoachInfoTable.id)

    fun getCoachAchievements(coachId: String): List<String>{
        val coachAchievements = transaction{
            return@transaction CoachAchievements.selectAll().where {
                CoachAchievements.coachId eq coachId
            }.map {
                return@map it[CoachAchievements.name]
            }
        }

        return coachAchievements
    }
}