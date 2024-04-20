package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object MuscleGroupsExcercises: Table("muscle_groups_excercises") {
    val id = integer("id").autoIncrement()
    val excerciseId = integer("excercise_id").references(Excercises.id)
    val muscleGroupId = integer("muscle_group_id").references(MuscleGroups.id)

    override val primaryKey = PrimaryKey(id)
}