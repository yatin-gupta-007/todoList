package com.example.Service

import com.example.Repository.ToDoRepository
import com.example.entities.ToDoResponse
import com.example.mappers.ResponseMapper
import javax.inject.Inject

class GetTaskService
@Inject
constructor(
    private val repository: ToDoRepository,
    private val mapper: ResponseMapper
) {
    suspend fun invoke(id: Int): ToDoResponse? {
        return try {
            repository.getTaskById(id)
                ?.let { mapper.toDomainModel(it) }
        } catch (e: Exception) {
            throw e
        }
    }
}