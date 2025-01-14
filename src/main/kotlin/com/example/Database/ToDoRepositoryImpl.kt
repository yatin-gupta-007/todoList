package com.example.Database

import com.example.Repository.ToDoRepository
import com.example.entities.ToDo
import com.example.entities.ToDoRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ToDoRepositoryImpl : ToDoRepository {
    override fun getAllTasks(): List<ToDo> = transaction {
        ToDoEntity.selectAll().map {
            ToDo(id = it[ToDoEntity.id], title = it[ToDoEntity.title], isDone = it[ToDoEntity.isDone])
        }
    }

    override fun getTaskById(id: Int): ToDo? = transaction {
        val result = ToDoEntity.select(ToDoEntity.id, ToDoEntity.title, ToDoEntity.isDone)
            .where { ToDoEntity.id eq id }
        result.mapNotNull {
            ToDo(
                id = it[ToDoEntity.id],
                title = it[ToDoEntity.title],
                isDone = it[ToDoEntity.isDone]
            )
        }.singleOrNull()
    }

    override fun createTask(todo: ToDoRequest): ToDo = transaction {
        val id = ToDoEntity.insert {
            it[title] = todo.title
            it[isDone] = false
        } get ToDoEntity.id
        commit()
        println("Inserted ID: $id")
        getTaskById(id) ?: throw IllegalArgumentException("Task not found after creation.")
    }

    override fun updateTask(id: Int, title: String, isDone: Boolean): ToDo = transaction {
        ToDoEntity.update({ ToDoEntity.id eq id }) {
            it[ToDoEntity.title] = title
            it[ToDoEntity.isDone] = isDone
        }
        getTaskById(id) ?: throw IllegalArgumentException("Task not found after update.")
    }

    override fun deleteTask(id: Int): Unit = transaction {
        ToDoEntity.deleteWhere { ToDoEntity.id eq id }
    }
}