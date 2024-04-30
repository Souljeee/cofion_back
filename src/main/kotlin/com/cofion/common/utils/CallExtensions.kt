package com.cofion.common.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

suspend fun ApplicationCall.checkAuth(){
    val authToken = this.request.header("auth-token")

    if (authToken == null) {
        this.respond(status = HttpStatusCode.Unauthorized, message = "Unauthorized")
    }
}