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
                val muscleGroups = exerciseController.getMuscleGroupsByExerciseId(exerciseId = it.id)

                ExerciseReponse(
                    id = it.id,
                    name = it.name,
                    type = it.type,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    videoUrl = it.videoUrl,
                    authorId = it.authorId,
                    difficulty = it.difficulty,
                    muscleGroups = muscleGroups,
                )
            }

            call.respond(exerciseResponses)
        }
        get("/exercise_details/{exerciseId}") {
            val exerciseId = call.parameters["exerciseId"]?.toInt()

            val exercise = exerciseController.getExerciseDetails(exerciseId = exerciseId!!)

            val muscleGroups = exerciseController.getMuscleGroupsByExerciseId(exerciseId = exercise.id)

            call.respond(
                ExerciseReponse(
                    id = exercise.id,
                    name = exercise.name,
                    type = exercise.type,
                    description = exercise.description,
                    imageUrl = exercise.imageUrl,
                    videoUrl = exercise.videoUrl,
                    authorId = exercise.authorId,
                    difficulty = exercise.difficulty,
                    muscleGroups = muscleGroups,
                )
            )
        }
    }
}