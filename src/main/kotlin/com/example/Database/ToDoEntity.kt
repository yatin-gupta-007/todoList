package com.example.Database

import org.jetbrains.exposed.sql.Table

object ToDoEntity : Table("todoentity") {
    val id = integer("id").autoIncrement()
    val title = varchar("task", 255)
    val isDone = bool("is_done")
    override val primaryKey = PrimaryKey(id, name = "PK_ToDoEntity_Id")
}