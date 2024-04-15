package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object ExcercisesApproaches: Table("excercises_approaches") {
    val id = integer("id").autoIncrement()
    val excerciseId = integer("excercise_id").references(Excercises.id)
    val approachId = integer("approach_id").references(ApproachesInfo.id)

    override val primaryKey = PrimaryKey(ExcercisesDetails.id)
}