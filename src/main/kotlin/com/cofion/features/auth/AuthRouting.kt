package com.cofion.features.auth

import com.cofion.common.database.tables.UsersTable
import io.ktor.http.*
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
        patch("/logout") {
            val payload = call.receive<LogoutPayload>()
            UsersTable.resetAuthToken(email = payload.email)
            call.respond(status = HttpStatusCode.OK, message = "Success logout")
        }
    }
}