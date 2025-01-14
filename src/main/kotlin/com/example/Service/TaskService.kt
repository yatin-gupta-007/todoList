package com.example.Service

import com.example.Repository.ToDoRepository
import com.example.entities.ToDo

class TaskService(private val repository: ToDoRepository) {
    fun updateTask(id: Int, title: String, isDone: Boolean): ToDo {
        return repository.updateTask(id, title, isDone);
    }

    fun getTaskById(id: Int): ToDo? {
        return repository.getTaskById(id);
    }
}
