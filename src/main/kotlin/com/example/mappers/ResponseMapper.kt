package com.example.mappers

import com.example.entities.ToDo
import com.example.entities.ToDoResponse
import javax.inject.Inject

class ResponseMapper
@Inject
constructor()
{
    fun toDomainModel(todo: ToDo) = ToDoResponse(
        id =todo.id,
        title = todo.title,
        isDone = todo.isDone
    )
}