package com.example.compose_template.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.compose_template.data.database.converter.DateConverters
import com.example.compose_template.data.database.dao.TodoDao
import com.example.compose_template.data.database.model.TodoData


@Database(
    entities = [
        TodoData::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        DateConverters::class,
    ]
)
abstract class TemplateDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}