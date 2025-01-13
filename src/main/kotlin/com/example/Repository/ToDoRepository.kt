package com.example.Repository

import com.example.entities.ToDo
import com.example.entities.ToDoRequest

interface ToDoRepository {
    fun getAllTasks(): List<ToDo>
    fun updateTask(id: Int, title: String, isDone: Boolean): ToDo
    fun getTaskById(id: Int): ToDo?
    fun createTask(todo: ToDoRequest): ToDo
    fun deleteTask(id: Int)
}