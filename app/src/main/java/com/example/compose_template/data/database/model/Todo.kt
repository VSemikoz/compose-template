package com.example.compose_template.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "todo")
data class TodoData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val desc: String,
    val creationDate: Date,
    val lastUpdateDate: Date,
    val isFavorite: Boolean,
)