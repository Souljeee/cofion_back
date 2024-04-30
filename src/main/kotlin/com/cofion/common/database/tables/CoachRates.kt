package com.cofion.common.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CoachRates: IntIdTable("coach_rates") {
    val rate = integer("rate")
    val positiveComment = varchar("positive_comment", 1000)
    val negativeComment = varchar("negative_comment", 1000)
    val coachId = varchar("coach_id", 100).references(CoachInfoTable.id)
}