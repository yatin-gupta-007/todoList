package com.example.Service

import com.example.Repository.ToDoRepository
import com.example.entities.ToDoRequest
import com.example.entities.ToDoResponse
import com.example.mappers.ResponseMapper
import javax.inject.Inject

class UpdateTaskService
@Inject
constructor(
    private val repository: ToDoRepository,
    private val mapper: ResponseMapper
) {
    suspend fun invoke(id: Int, request: ToDoRequest): ToDoResponse? {
        return try {
            request.title?.let { s ->
                repository.updateTask(id, s, request.isDone)
                    .let { mapper.toDomainModel(it) }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
