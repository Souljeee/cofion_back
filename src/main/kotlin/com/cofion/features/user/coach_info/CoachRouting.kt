package com.cofion.features.user.coach_info

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.coachRouting(){
    val coachController = CoachController()

    routing {
        get ("/coach_info/{userId}"){
            val authToken = call.request.header("auth-token")

            if (authToken == null) {
                call.respond(status = HttpStatusCode.Unauthorized, message = "Unauthorized")

                return@get
            }

            val userId = call.parameters["userId"]
        }
    }
}