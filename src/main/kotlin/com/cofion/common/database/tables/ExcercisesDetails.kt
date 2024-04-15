package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object ExcercisesDetails: Table("excercises_details") {
    val id = integer("id").autoIncrement()
    val baseExcerciseId = integer("base_excercise_id").references(Excercises.id)
    val comment = text("comment").nullable()

    override val primaryKey = PrimaryKey(id)
}