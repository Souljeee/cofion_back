package com.cofion.features.user.coach_info

import com.cofion.common.database.tables.CoachInfoTable
import com.cofion.common.database.tables.CoachRates
import com.cofion.common.database.tables.UsersTable

class CoachController {
    fun getCoachInfo(userId: String): CoachInfoDto{
        val coachId = UsersTable.getCoachId(userId = userId)

        val coachInfo = CoachInfoTable.getCoachInfo(id = coachId)

        return coachInfo
    }

    fun getCoachRates(coachId: String): List<CoachRateDto>{
        return CoachRates.getCoachRates(coachId = coachId)
    }
}