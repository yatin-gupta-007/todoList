package com.example.di

import com.example.modules.DatabaseModule
import com.example.modules.RepositoryModule
import dagger.Component
import org.jetbrains.exposed.sql.Database
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface RootComponent {
    val database: Database
    val toDoRepository: com.example.Repository.ToDoRepository
}