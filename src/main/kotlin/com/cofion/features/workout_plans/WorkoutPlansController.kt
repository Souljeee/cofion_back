package com.cofion.features.workout_plans

import com.cofion.common.database.tables.*
import org.jetbrains.exposed.sql.transactions.transaction

class WorkoutPlansController {
    fun createWorkoutPlan(workoutPlanPayload: WorkoutPlanPayload) {
        transaction {
            val workoutPlanId = WorkoutPlans.insertWorkoutPlan(
                title = workoutPlanPayload.title,
                description = workoutPlanPayload.description,
                sessionsPerWeek = workoutPlanPayload.sessionPerWeek,
                generalDuration = workoutPlanPayload.planDuration,
                authorId = workoutPlanPayload.authorId,
                difficulty = workoutPlanPayload.difficulty,
            )

            workoutPlanPayload.sessions.forEach { session ->
                val sessionId = WorkoutSessions.insertWorkoutSession(
                    duration = session.duration,
                    orderNumber = session.orderNumber,
                    type = session.type,
                    workoutPlanId = workoutPlanId,
                )

                session.exercises.forEach { exercisePayload ->
                    val exerciseId = ExcercisesDetails.insertExerciseDetails(
                        baseExcerciseId = exercisePayload.exerciseId,
                        comment = exercisePayload.comment,
                    )

                    ExcercisesSessions.insertExercisesSessionsConnection(
                        sessionId = sessionId,
                        exerciseId = exerciseId,
                        orderNumber = exercisePayload.orderNumber
                    )

                    exercisePayload.approach.forEach {
                        val approachId = ApproachesInfo.insertApproaches(
                            orderNumber = it.orderNumber,
                            repsCount = it.repsCount,
                            weight = it.weight,
                            rest = it.rest,
                        )

                        ExcercisesApproaches.insertExerciseApproachConnection(
                            exerciseId = exerciseId,
                            approachId = approachId,
                        )
                    }
                }
            }
        }
    }
}