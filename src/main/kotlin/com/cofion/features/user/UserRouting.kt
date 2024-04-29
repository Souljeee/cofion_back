package com.cofion.features.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.toKotlinLocalDate

fun Application.userRouting(){
    val userController = UserController()
    routing {
        get("/user_info"){
            val authToken = call.request.header("auth-token")

            if (authToken == null) {
                call.respond(status = HttpStatusCode.Unauthorized, message = "Unauthorized")

                return@get
            }

            val user = userController.getUserInfo(authToken = authToken)

            if(user == null){
                call.respond(status = HttpStatusCode.NoContent, message = "User not exist")

                return@get
            }

            call.respond(
                UserResponse(
                    id = user.id,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    createAccountDate = user.createAccountDate.toKotlinLocalDate(),
                    email = user.email,
                    accountType = user.accountType,
                )
            )
        }
    }
}