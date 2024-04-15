package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object ApproachesInfo: Table("approaches_info") {
    val id = integer("id").autoIncrement()
    val orderNumber = integer("order_number")
    val repsCount = integer("reps_count")
    val weight = integer("weight")
    val rest = integer("rest").nullable()


    override val primaryKey = PrimaryKey(id)
}