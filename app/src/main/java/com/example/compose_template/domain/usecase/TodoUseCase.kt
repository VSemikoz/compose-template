package com.example.compose_template.domain.usecase

import com.example.compose_template.domain.entity.todo.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.todo.TodoItemEntity
import com.example.compose_template.domain.repository.ITodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoUseCase @Inject constructor(
    private val todoRepository: ITodoRepository
) {
    suspend fun getAllTodo(): List<TodoItemEntity> {
        return todoRepository.getAllTodo()
    }

    suspend fun getById(id: Int): TodoItemEntity {
        return todoRepository.getById(id)
    }

    fun subscribeAll(): Flow<List<TodoItemEntity>> {
        return todoRepository.subscribeAll()
    }

    suspend fun addNew(item: TodoItemAddNewEntity) {
        todoRepository.addNew(item)
    }

    suspend fun deleteById(id: Int) {
        todoRepository.deleteById(id)
    }

    suspend fun update(entity: TodoItemEntity) {
        todoRepository.update(entity)
    }

    suspend fun setFavorite(id: Int, isFavorite: Boolean) {
        todoRepository.setFavorite(id, isFavorite)
    }
}

