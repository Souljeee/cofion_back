package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object ExcercisesApproaches: Table("excercises_approaches") {
    val id = integer("id").autoIncrement()
    val excerciseId = integer("excercise_id").references(Excercises.id)
    val approachId = integer("approach_id").references(ApproachesInfo.id)

    override val primaryKey = PrimaryKey(ExcercisesDetails.id)

    fun insertExerciseApproachConnection(exerciseId: Int, approachId: Int){
        ExcercisesApproaches.insert {
            it[ExcercisesApproaches.excerciseId] = exerciseId
            it[ExcercisesApproaches.approachId] = approachId
        }
    }
}