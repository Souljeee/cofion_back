package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object MuscleGroups: Table("muscle_groups") {
    val id = integer("id").autoIncrement()
    val key = varchar("key",100)

    override val primaryKey = PrimaryKey(id)
}