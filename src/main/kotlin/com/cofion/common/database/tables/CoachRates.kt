package com.cofion.common.database.tables

import com.cofion.features.user.coach_info.CoachRateDto
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object CoachRates: IntIdTable("coach_rates") {
    val rate = integer("rate")
    val positiveComment = varchar("positive_comment", 1000)
    val negativeComment = varchar("negative_comment", 1000)
    val coachId = varchar("coach_id", 100).references(CoachInfoTable.id)

    fun getCoachRates(coachId: String): List<CoachRateDto>{
        val coachRates = transaction{
            return@transaction CoachRates.selectAll().where {
                CoachRates.coachId eq coachId
            }.map {
                return@map CoachRateDto(
                    rate = it[CoachRates.rate],
                    positiveComment = it[CoachRates.positiveComment],
                    negativeComment = it[CoachRates.negativeComment],
                )
            }
        }

        return coachRates
    }
}