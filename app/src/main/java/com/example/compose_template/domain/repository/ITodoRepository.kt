package com.example.compose_template.domain.repository

import com.example.compose_template.domain.entity.todo.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.todo.TodoItemEntity
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    suspend fun getAllTodo(): List<TodoItemEntity>
    suspend fun getById(id: Int): TodoItemEntity
    fun subscribeAll(): Flow<List<TodoItemEntity>>
    suspend fun addNew(item: TodoItemAddNewEntity)
    suspend fun deleteById(id: Int)
    suspend fun update(entity: TodoItemEntity)
    suspend fun setFavorite(id: Int, isFavorite: Boolean)
}
