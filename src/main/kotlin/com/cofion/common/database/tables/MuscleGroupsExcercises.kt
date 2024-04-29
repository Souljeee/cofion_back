package com.cofion.common.database.tables

import com.cofion.features.excercises.dtos.ExcerciseDto
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object MuscleGroupsExcercises : Table("muscle_groups_excercises") {
    val id = integer("id").autoIncrement()
    val excerciseId = integer("excercise_id").references(Excercises.id)
    val muscleGroupId = integer("muscle_group_id").references(MuscleGroups.id)

    override val primaryKey = PrimaryKey(id)

    fun getMuscleGroupsIdByExerciseId(exerciseId: Int): List<Int> {
        val muscleGroupsIds: MutableList<Int> = mutableListOf()

        transaction {
            MuscleGroupsExcercises.select { MuscleGroupsExcercises.excerciseId eq exerciseId }
                .forEach { muscleGroupsIds.add(it[MuscleGroupsExcercises.muscleGroupId]) }
        }

        return muscleGroupsIds
    }
}