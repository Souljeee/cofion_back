package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.insert

object WorkoutPlans : IntIdTable("workout_plans") {
    val title = varchar("title", 200)
    val description = text("description").nullable()
    val sessionsPerWeek = integer("sessions_per_week_count")
    val generalDuration = integer("general_duration")
    val authorId = varchar("author_id", 100).references(UsersTable.id)
    val difficulty = varchar("difficulty", 50)

    fun insertWorkoutPlan(
        title: String,
        description: String?,
        sessionsPerWeek: Int,
        generalDuration: Int,
        authorId: String,
        difficulty: String,
    ): Int {
        val id = WorkoutPlans.insert {
            it[WorkoutPlans.title] = title
            it[WorkoutPlans.description] = description
            it[WorkoutPlans.sessionsPerWeek] = sessionsPerWeek
            it[WorkoutPlans.generalDuration] = generalDuration
            it[WorkoutPlans.authorId] = authorId
            it[WorkoutPlans.difficulty] = difficulty
        } get WorkoutPlans.id

        return id.value
    }
}