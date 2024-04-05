package com.example.compose_template.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.compose_template.data.models.todo.TodoData
import com.example.compose_template.domain.entity.todo.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.todo.TodoItemEntity
import com.squareup.moshi.Moshi
import java.time.Instant
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedTypeConverter
class TodoConverter @Inject constructor(moshi: Moshi) {
    private val todoDataConverter = moshi.adapter(TodoData::class.java)

    @TypeConverter
    fun fromEntityToJson(entity: TodoData): String = todoDataConverter.toJson(entity)

    @TypeConverter
    fun toEntityFromJson(entity: String): TodoData =
        checkNotNull(todoDataConverter.fromJson(entity))

    @TypeConverter
    fun toTodoData(entity: TodoItemEntity): TodoData = entity.intoData()

    @TypeConverter
    fun toTodoEntity(entity: TodoData): TodoItemEntity = entity.intoDomain()


    @TypeConverter
    fun toTodoData(entity: TodoItemAddNewEntity): TodoData = entity.intoData()

}


private fun TodoData.intoDomain(): TodoItemEntity {
    return TodoItemEntity(
        id = id,
        name = name,
        desc = desc,
        creationDate = creationDate,
        lastUpdateDate = lastUpdateDate,
        isFavorite = isFavorite,
    )
}

private fun TodoItemEntity.intoData(): TodoData {
    return TodoData(
        id = id,
        name = name,
        desc = desc,
        creationDate = creationDate,
        lastUpdateDate = lastUpdateDate,
        isFavorite = isFavorite,
    )
}

private fun TodoItemAddNewEntity.intoData(): TodoData {
    return TodoData(
        name = name,
        desc = desc,
        creationDate = creationDate,
        lastUpdateDate = Date.from(Instant.now()),
        isFavorite = false,
    )
}