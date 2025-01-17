package com.example.Repository

import com.example.entities.ToDo
import com.example.entities.ToDoRequest

interface ToDoRepository {
    suspend fun getAllTasks(): List<ToDo>
    suspend fun updateTask(id: Int, title: String, isDone: Boolean): ToDo
    suspend fun getTaskById(id: Int): ToDo?
    suspend fun createTask(todo: ToDoRequest): ToDo
    suspend fun deleteTask(id: Int)
}