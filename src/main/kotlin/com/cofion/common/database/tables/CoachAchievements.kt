package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CoachAchievements: IntIdTable("coach_achievements") {
    val name = varchar("name", 500)
    val coachId = varchar("coach_id", 100).references(CoachInfoTable.id)
}