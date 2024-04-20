package com.cofion.features.excercises

import com.cofion.common.database.tables.Excercises
import com.cofion.features.excercises.dtos.ExcerciseDto

class ExcercisesController{
    fun getAllExercises(): List<ExcerciseDto>{
        val exercises = Excercises.getAllExercises();

        return exercises
    }

    fun getExerciseDetails(exerciseId: Int): ExcerciseDto{
        val exerciseDetails = Excercises.getExerciseById(exerciseId = exerciseId)

        return exerciseDetails
    }
}