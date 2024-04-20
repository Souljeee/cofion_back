package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object ExcercisesDetails: Table("excercises_details") {
    val id = integer("id").autoIncrement()
    val baseExcerciseId = integer("base_excercise_id").references(Excercises.id)
    val comment = text("comment").nullable()

    override val primaryKey = PrimaryKey(id)

    fun insertExerciseDetails(baseExcerciseId: Int, comment: String?): Int{
        val id = ExcercisesDetails.insert {
            it[ExcercisesDetails.baseExcerciseId] = baseExcerciseId
            it[ExcercisesDetails.comment] = comment
        } get ExcercisesDetails.id

        return id
    }
}