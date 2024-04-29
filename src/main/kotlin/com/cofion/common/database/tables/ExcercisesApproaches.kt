package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object ExcercisesApproaches: IntIdTable("excercises_approaches") {
    val excerciseId = integer("exercise_id").references(Excercises.id)
    val approachId = integer("approach_id").references(ApproachesInfo.id)

    fun insertExerciseApproachConnection(exerciseId: Int, approachId: Int){
        ExcercisesApproaches.insert {
            it[ExcercisesApproaches.excerciseId] = exerciseId
            it[ExcercisesApproaches.approachId] = approachId
        }
    }
}