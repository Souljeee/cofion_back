package com.cofion.common.database.tables

import org.jetbrains.exposed.sql.Table

object WorkoutPlansExcercises: Table("workout_plans_excercises") {
    val id = integer("id").autoIncrement()
    val workoutPlanId = integer("workout_plan_id")
    val excerciseId = integer("excercise_id")
    val orderNumber = integer("order_number")

    override val primaryKey = PrimaryKey(ExcercisesDetails.id)
}