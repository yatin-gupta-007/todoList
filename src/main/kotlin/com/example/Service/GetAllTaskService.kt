package com.example.Service

import com.example.Repository.ToDoRepository
import com.example.entities.ToDoResponse
import com.example.mappers.ResponseMapper
import javax.inject.Inject

class GetAllTaskService
@Inject
constructor(
    private val repository: ToDoRepository,
    private val mapper: ResponseMapper
) {
    suspend fun invoke(): List<ToDoResponse>? {
        return try {
            repository.getAllTasks()
                .map { mapper.toDomainModel(it) }
        } catch (e: Exception) {
            throw e
        }
    }

}