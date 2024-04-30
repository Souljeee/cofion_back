package com.cofion.features.user.coach_info

import com.cofion.common.utils.checkAuth
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.coachRouting(){
    val coachController = CoachController()

    routing {
        get ("/coach_info/{userId}"){
            call.checkAuth()

            val userId = call.parameters["userId"]

            if(userId == null){
                call.respond(status = HttpStatusCode.BadRequest, message = "userId is required")

                return@get
            }

            val coachInfoDto = coachController.getCoachInfo(userId = userId)

            call.respond(
                CoachInfoResponse(
                    coachId = coachInfoDto.coachId,
                    experience = coachInfoDto.experience,
                    description = coachInfoDto.description,
                    rating = coachInfoDto.rating,
                )
            )
        }
        get("/coach_rates/{coachId}"){
            call.checkAuth()

            val coachId = call.parameters["coachId"]

            if(coachId == null){
                call.respond(status = HttpStatusCode.BadRequest, message = "coachId is required")

                return@get
            }

            val coachRateDtos = coachController.getCoachRates(coachId = coachId)

            call.respond(
                coachRateDtos.map {
                    return@map CoachRateResponse(
                        rate = it.rate,
                        positiveComment = it.positiveComment,
                        negativeComment = it.negativeComment,
                    )
                }
            )
        }
    }
}