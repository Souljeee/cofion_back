package com.cofion.features.workout_plans

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.workoutPlansRouting(){
    val workoutPlansController = WorkoutPlansController()

    routing {
        post("/create_workout_plan"){
            val workoutPayload = call.receive<WorkoutPlanPayload>()

            workoutPlansController.createWorkoutPlan(workoutPlanPayload = workoutPayload)

            call.respond(message = "Created")
        }
    }
}