package com.example.compose_template.data.repository

import com.example.compose_template.data.converter.intoData
import com.example.compose_template.data.converter.intoDomain
import com.example.compose_template.data.database.TemplateDatabase
import com.example.compose_template.domain.entity.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.TodoItemEntity
import com.example.compose_template.domain.repository.ITodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val database: TemplateDatabase,
) : ITodoRepository {
    override suspend fun getAllTodo(): List<TodoItemEntity> {
        return database.todoDao.getAll().map { it.intoDomain() }
    }

    override suspend fun getById(id: Int): TodoItemEntity {
        return database.todoDao.getById(id).intoDomain()
    }

    override fun subscribeAll(): Flow<List<TodoItemEntity>> {
        return database.todoDao.flowAll().map { list -> list.map { it.intoDomain() } }
    }

    override suspend fun addNew(item: TodoItemAddNewEntity) {
        database.todoDao.addNew(item.intoData())
    }

    override suspend fun deleteById(id: Int) {
        database.todoDao.deleteById(id)
    }

    override suspend fun update(entity: TodoItemEntity) {
        database.todoDao.update(entity.intoData())
    }

    override suspend fun setFavorite(id: Int, isFavorite: Boolean) {
        database.todoDao.updateFavoriteById(id, isFavorite)
    }
}