package com.cofion.features.create_account

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

fun Application.createAccountRouting() {
    val createAccountController = CreateAccountController()

    routing {
        post("/create_account") {
            val payload = call.receive<CreateAccountPayload>()

            val hasAccessForCreatingAccount =
                createAccountController.checkAccessForCreatingAccount(email = payload.email)

            if (hasAccessForCreatingAccount) {
                call.respond(
                    CreateAccountResponse(
                        accessAllowed = true,
                        message = "Has access to create new user"
                    )
                )
            } else {
                call.respond(
                    CreateAccountResponse(
                        accessAllowed = false,
                        message = "User already exist"
                    )
                )
            }
        }
    }
}