package com.cofion.features.excercises

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.exercisesRouting() {
    val exerciseController = ExcercisesController()

    routing {
        get("/exercises_list") {
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
        get("/exercise_details/{exerciseId}") {
            val exerciseId = call.parameters["exerciseId"]?.toInt()

            val exercises = exerciseController.getExerciseDetails(exerciseId = exerciseId!!)

            call.respond(
                ExerciseReponse(
                    id = exercises.id,
                    name = exercises.name,
                    type = exercises.type,
                    description = exercises.description,
                    imageUrl = exercises.imageUrl,
                    videoUrl = exercises.videoUrl,
                    authorId = exercises.authorId,
                    difficulty = exercises.difficulty,
                )
            )
        }
    }
}