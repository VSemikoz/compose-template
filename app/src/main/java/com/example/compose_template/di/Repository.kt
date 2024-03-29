package com.example.compose_template.di

import com.example.compose_template.data.repository.TodoRepository
import com.example.compose_template.domain.repository.ITodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTodoRepository(todoRepository: TodoRepository): ITodoRepository = todoRepository
}
