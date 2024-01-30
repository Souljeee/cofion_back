package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object CoachInfoTable: Table("coach_info") {
    val id = varchar("id", 100)

    override val primaryKey = PrimaryKey(id)
}