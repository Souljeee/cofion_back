package com.cofion.features.create_account

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.createAccountRouting() {
    val createAccountController = CreateAccountController()

    routing {
        post("/create_account") {
            val payload = call.receive<CreateAccountPayload>()

            val accountCreated =
                createAccountController.createUnconfirmedUser(accountInfo = payload)

            if (accountCreated) {
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
        post("/create_code") {
            val payload = call.receive<CreateCodePayload>()

            val codeSent = createAccountController.createCode(email = payload.email)

            call.respond(
                CreateCodeResponse(codeSent = codeSent)
            )
        }
    }
}