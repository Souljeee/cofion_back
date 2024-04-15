package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object WorkoutSessions: Table("workout_sessions") {
    val id = integer("id").autoIncrement()
    val duration = integer("duration")
    val type = varchar("type", 100)
    val orderNumber = integer("order_number")
    val workoutPlanId = integer("workout_plan_id").references(WorkoutPlans.id)

    override val primaryKey = PrimaryKey(ExcercisesDetails.id)
}