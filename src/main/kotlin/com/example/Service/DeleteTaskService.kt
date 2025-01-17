package com.example.Service

import com.example.Repository.ToDoRepository
import com.example.mappers.ResponseMapper
import javax.inject.Inject

class DeleteTaskService
@Inject
constructor(
    private val repository: ToDoRepository,
) {
    suspend fun invoke(id: Int): Boolean{
        return try {
            repository.deleteTask(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}