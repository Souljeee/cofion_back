package com.cofion.features.auth

import com.cofion.features.create_account.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRouting() {
    val authController = AuthController()

    routing {
        post("/login") {
            val payload = call.receive<AuthPayload>()

            val authStatus = authController.authUser(email = payload.email, password = payload.password)

            call.respond(
                AuthResponse(
                    authStatus = authStatus.authStatus,
                    token = authStatus.token
                )
            )
        }
        post("/logout") {}
    }
}