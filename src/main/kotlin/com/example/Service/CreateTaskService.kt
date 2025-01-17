package com.example.Service

import com.example.Repository.ToDoRepository
import com.example.entities.ToDoRequest
import com.example.entities.ToDoResponse
import com.example.mappers.ResponseMapper
import javax.inject.Inject

class CreateTaskService
@Inject
constructor(
    private val repository: ToDoRepository,
    private val mapper: ResponseMapper
) {
    suspend fun invoke(request: ToDoRequest): ToDoResponse? {
        return try {
            repository.createTask(request)
                .let { mapper.toDomainModel(it) }
        } catch (e: Exception) {
            throw e
        }
    }
}