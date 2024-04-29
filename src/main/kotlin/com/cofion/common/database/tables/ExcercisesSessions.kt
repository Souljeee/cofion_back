package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object ExcercisesSessions : IntIdTable("excercises_sessions") {
    val sessionId = integer("session_id").references(WorkoutSessions.id)
    val excerciseId = integer("exercise_id").references(ExcercisesDetails.id)
    val orderNumber = integer("order_number")


    fun insertExercisesSessionsConnection(sessionId: Int, exerciseId: Int, orderNumber: Int) {
        ExcercisesSessions.insert {
            it[ExcercisesSessions.sessionId] = sessionId
            it[ExcercisesSessions.excerciseId] = exerciseId
            it[ExcercisesSessions.orderNumber] = orderNumber
        }
    }
}