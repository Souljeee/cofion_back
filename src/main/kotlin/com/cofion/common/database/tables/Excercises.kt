package com.cofion.common.database.tables

import com.cofion.features.excercises.dtos.ExcerciseDto
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Excercises : Table("excercises") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 300)
    val type = varchar("type", 50)
    val description = text("description").nullable()
    val imageUrl = varchar("image_url", 200).nullable()
    val videoUrl = varchar("video_url", 200).nullable()
    val authorId = varchar("author_id", 100).references(UsersTable.id).nullable()
    val difficulty = varchar("difficulty", 50).nullable()

    override val primaryKey = PrimaryKey(id)

    fun getAllExercises() : List<ExcerciseDto>{
        val exercisesList: MutableList<ExcerciseDto> = mutableListOf()

        val exercises = transaction {
            Excercises.selectAll().forEach {
                exercisesList.add(
                    ExcerciseDto(
                        id = it[Excercises.id],
                        name = it[Excercises.name],
                        type = it[Excercises.type],
                        description = it[Excercises.description],
                        imageUrl = it[Excercises.imageUrl],
                        videoUrl = it[Excercises.videoUrl],
                        authorId = it[Excercises.authorId],
                        difficulty = it[Excercises.difficulty],
                    )
                )
            }

            return@transaction exercisesList
        }

        return exercises
    }

    fun getExerciseById(exerciseId: Int) : ExcerciseDto{
        val exerciseList: MutableList<ExcerciseDto> = mutableListOf()

        transaction{
            Excercises.select{Excercises.id eq exerciseId}.forEach {
                exerciseList.add(
                    ExcerciseDto(
                        id = it[Excercises.id],
                        name = it[Excercises.name],
                        type = it[Excercises.type],
                        description = it[Excercises.description],
                        imageUrl = it[Excercises.imageUrl],
                        videoUrl = it[Excercises.videoUrl],
                        authorId = it[Excercises.authorId],
                        difficulty = it[Excercises.difficulty],
                    )
                )
            }
        }

        return exerciseList.first()
    }
}