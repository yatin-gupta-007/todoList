package com.example.di

import com.example.Repository.ToDoRepository
import com.example.TaskHttpService.*
import dagger.Component
import dagger.Provides

@TaskHttpScope
@Component(dependencies = [RootComponent::class])
interface TaskHttpComponent {
    val updateTaskHttpService: UpdateTaskHttpService
    val createTaskHttpService: CreateTaskHttpService
    val deleteTaskHttpService: DeleteTaskHttpService
    val getAllHttpService: GetAllHttpService
    val getTaskHttpService: GetTaskHttpService

}