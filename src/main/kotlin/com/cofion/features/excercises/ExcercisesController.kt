package com.cofion.features.excercises

import com.cofion.common.database.tables.Excercises
import com.cofion.common.database.tables.MuscleGroups
import com.cofion.common.database.tables.MuscleGroupsExcercises
import com.cofion.features.excercises.dtos.ExcerciseDto

class ExcercisesController{
    fun getAllExercises(): List<ExcerciseDto>{
        val exercises = Excercises.getAllExercises();

        return exercises
    }

    fun getExerciseDetails(exerciseId: Int): ExcerciseDto {
        val exerciseDetails = Excercises.getExerciseById(exerciseId = exerciseId)

        return exerciseDetails
    }

    fun getMuscleGroupsByExerciseId(exerciseId: Int): List<String> {
        val muscleGroupsIds = MuscleGroupsExcercises.getMuscleGroupsIdByExerciseId(exerciseId = exerciseId)

        val muscleGroupsKeys:  MutableList<String> = mutableListOf()

        muscleGroupsIds.forEach {
            val muscleGroupKey = MuscleGroups.getMuscleGroupById(muscleGroupId = it)

            muscleGroupsKeys.add(muscleGroupKey)
        }

        return muscleGroupsKeys
    }
}