package com.example.modules

import com.example.Database.ToDoRepositoryImpl
import com.example.Repository.ToDoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(): ToDoRepository {
        return ToDoRepositoryImpl()
    }
}