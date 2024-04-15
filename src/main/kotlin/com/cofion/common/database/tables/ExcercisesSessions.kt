package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object ExcercisesSessions: Table("sessions_excercises") {
    val id = integer("id").autoIncrement()
    val sessionId = integer("session_id").references(WorkoutSessions.id)
    val excerciseId = integer("excercise_id").references(ExcercisesDetails.id)
    val orderNumber = integer("order_number")

    override val primaryKey = PrimaryKey(ExcercisesDetails.id)
}