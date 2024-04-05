package com.example.compose_template.data.repository

import com.example.compose_template.data.converter.TodoConverter
import com.example.compose_template.data.database.TemplateDatabase
import com.example.compose_template.domain.entity.todo.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.todo.TodoItemEntity
import com.example.compose_template.domain.repository.ITodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val database: TemplateDatabase,
    private val todoConverter: TodoConverter,

    ) : ITodoRepository {
    override suspend fun getAllTodo(): List<TodoItemEntity> {
        return database.todoDao.getAll().map { todoConverter.toTodoEntity(it) }
    }

    override suspend fun getById(id: Int): TodoItemEntity {
        return todoConverter.toTodoEntity(database.todoDao.getById(id))
    }

    override fun subscribeAll(): Flow<List<TodoItemEntity>> {
        return database.todoDao.flowAll().map { list -> list.map { todoConverter.toTodoEntity(it) } }
    }

    override suspend fun addNew(item: TodoItemAddNewEntity) {
        database.todoDao.addNew(todoConverter.toTodoData(item))
    }

    override suspend fun deleteById(id: Int) {
        database.todoDao.deleteById(id)
    }

    override suspend fun update(entity: TodoItemEntity) {
        database.todoDao.update(todoConverter.toTodoData(entity))
    }

    override suspend fun setFavorite(id: Int, isFavorite: Boolean) {
        database.todoDao.updateFavoriteById(id, isFavorite)
    }
}