package com.example.Database

import com.example.Repository.ToDoRepository
import com.example.entities.ToDo
import com.example.entities.ToDoRequest
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class ToDoRepositoryImpl : ToDoRepository {
    override suspend fun getAllTasks(): List<ToDo> = transaction {
        ToDoEntity.selectAll().map {
            ToDo(id = it[ToDoEntity.id], title = it[ToDoEntity.title], isDone = it[ToDoEntity.isDone])
        }
    }

    override suspend fun getTaskById(id: Int): ToDo? = transaction {
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

    override suspend fun createTask(todo: ToDoRequest): ToDo = transaction {
        val id = ToDoEntity.insert {
            it[title] = todo.title?: ""
            it[isDone] = false
        } get ToDoEntity.id
        ToDoEntity.selectAll().where { ToDoEntity.id eq id }.map {
            ToDo(
                id = it[ToDoEntity.id],
                title = it[ToDoEntity.title],
                isDone = it[ToDoEntity.isDone]
            )
        }.single()
    }

    override suspend fun updateTask(id: Int, title: String, isDone: Boolean): ToDo = transaction {
        ToDoEntity.update({ ToDoEntity.id eq id }) {
            it[ToDoEntity.title] = title
            it[ToDoEntity.isDone] = isDone
        }
        ToDoEntity.selectAll().where { ToDoEntity.id eq id }.map {
            ToDo(
                id = it[ToDoEntity.id],
                title = it[ToDoEntity.title],
                isDone = it[ToDoEntity.isDone]
            )
        }.single()
    }

    override suspend fun deleteTask(id: Int): Unit = transaction {
        ToDoEntity.deleteWhere { ToDoEntity.id eq id }
    }
}