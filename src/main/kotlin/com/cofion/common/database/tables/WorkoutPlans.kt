package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object WorkoutPlans: Table("workout_plans") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 200)
    val description = text("description").nullable()
    val sessionsPerWeek = integer("sessions_per_week")
    val generalDuration = integer("general_duration")
    val authorId = varchar("author_id", 100).references(UsersTable.id)
    val difficulty = varchar("difficulty", 50)

    override val primaryKey = PrimaryKey(ExcercisesDetails.id)
}