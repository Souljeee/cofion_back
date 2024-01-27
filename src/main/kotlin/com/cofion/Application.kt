package com.cofion

import com.cofion.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/cofion",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "1q2w3e4r5t",
    )
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureSockets()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
