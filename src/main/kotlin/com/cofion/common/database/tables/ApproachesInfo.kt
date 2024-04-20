package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

object ApproachesInfo: Table("approaches_info") {
    val id = integer("id").autoIncrement()
    val orderNumber = integer("order_number")
    val repsCount = integer("reps_count")
    val weight = double("weight")
    val rest = integer("rest")


    override val primaryKey = PrimaryKey(id)

    fun insertApproaches(orderNumber: Int, repsCount: Int, weight: Double, rest: Int) : Int{
        val id = ApproachesInfo.insert {
            it[ApproachesInfo.orderNumber] = orderNumber
            it[ApproachesInfo.repsCount] = repsCount
            it[ApproachesInfo.weight] = weight
            it[ApproachesInfo.rest] = rest
        } get ApproachesInfo.id

        return id
    }
}