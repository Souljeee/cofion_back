package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object MuscleGroups: Table("muscle_groups") {
    val id = integer("id").autoIncrement()
    val key = varchar("key",100)

    override val primaryKey = PrimaryKey(id)

    fun getMuscleGroupById(muscleGroupId: Int): String{
        val muscleGroupsKey: MutableList<String> = mutableListOf()

        transaction {
            MuscleGroups.select{MuscleGroups.id eq muscleGroupId}.forEach {
                muscleGroupsKey.add(it[MuscleGroups.key])
            }
        }

        return muscleGroupsKey.first()
    }

    fun getAllMuscleGroups(): List<String>{
        val muscleGroups: MutableList<String> = mutableListOf()

        transaction {
            MuscleGroups.selectAll().forEach {
                muscleGroups.add(it[MuscleGroups.key])
            }
        }

        return muscleGroups
    }
}