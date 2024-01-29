package com.cofion.features.auth

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
            authController.resetAuthToken(email = payload.email)
            call.respond(status = HttpStatusCode.OK, message = "Success logout")
        }
        get("/check_auth") {
            val authToken = call.request.header("auth-token")

            if (authToken == null) {
                call.respond(status = HttpStatusCode.Unauthorized, message = "Unauthorized")

                return@get
            }

            val hasAuth = authController.checkAuth(authToken = authToken)

            call.respond(
                CheckAuthResponse(
                    hasAccess = hasAuth
                )
            )
        }
    }
}