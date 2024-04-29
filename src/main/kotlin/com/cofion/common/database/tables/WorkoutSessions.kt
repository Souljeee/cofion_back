package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object WorkoutSessions : IntIdTable("workout_sessions") {
    val duration = integer("duration")
    val type = varchar("type", 100)
    val orderNumber = integer("order_number")
    val workoutPlanId = integer("workout_plan_id").references(WorkoutPlans.id)

    fun insertWorkoutSession(duration: Int, type: String, orderNumber: Int, workoutPlanId: Int): Int {
        val id = WorkoutSessions.insert {
            it[WorkoutSessions.duration] = duration
            it[WorkoutSessions.type] = type
            it[WorkoutSessions.orderNumber] = orderNumber
            it[WorkoutSessions.workoutPlanId] = workoutPlanId
        } get WorkoutSessions.id

        return id.value
    }
}