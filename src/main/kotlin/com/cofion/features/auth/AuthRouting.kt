package com.cofion.features.auth

import com.cofion.features.create_account.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRouting(){
    routing {
        post("/login") {
            val payload = call.receive<AuthPayload>()

        }
        post("/logout") {}
    }
}