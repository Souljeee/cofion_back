package com.cofion

import com.cofion.features.auth.authRouting
import com.cofion.features.create_account.createAccountRouting
import com.cofion.features.excercises.exercisesRouting
import com.cofion.features.user.userRouting
import com.cofion.features.workout_plans.workoutPlansRouting
import com.cofion.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/cofion",
        driver = "org.postgresql.Driver",
        user = System.getenv("DB_USER"),
        password = System.getenv("DB_PASSWORD"),
    )
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    authRouting()
    createAccountRouting()
    configureMonitoring()
    configureSockets()
    configureSerialization()
    configureHTTP()
    configureRouting()
    exercisesRouting()
    workoutPlansRouting()
    userRouting()
}
