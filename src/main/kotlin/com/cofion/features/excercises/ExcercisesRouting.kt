package com.cofion.features.excercises

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.exercisesRouting() {
    val exerciseController = ExcercisesController()

    routing {
        get("exercises_list") {
            val exercises = exerciseController.getAllExercises()

            val exerciseResponses = exercises.map {
                ExerciseReponse(
                    id = it.id,
                    name = it.name,
                    type = it.type,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    videoUrl = it.videoUrl,
                    authorId = it.authorId,
                    difficulty = it.difficulty,
                )
            }

            call.respond(exerciseResponses)
        }
    }
}