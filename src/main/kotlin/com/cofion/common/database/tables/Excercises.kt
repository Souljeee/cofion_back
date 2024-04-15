package com.cofion.common.database.tables

import com.cofion.common.database.tables.UsersTable.references
import org.jetbrains.exposed.sql.Table

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
}