package com.example.modules

import dagger.Module
import dagger.Provides
import org.jetbrains.exposed.sql.Database
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDB(): Database {
        println("Connected to database")

        return Database.connect(
            url = "jdbc:postgresql://localhost:5432/todolist",
            user = "yatin.gupta",
            driver = "org.postgresql.Driver",
            password = ""
        )
    }
}