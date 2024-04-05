package com.example.compose_template.domain.entity.todo

import java.util.Date

data class TodoItemEntity(
    val id: Int,
    val name: String,
    val desc: String,
    val creationDate: Date,
    val lastUpdateDate: Date,
    val isFavorite: Boolean,
)