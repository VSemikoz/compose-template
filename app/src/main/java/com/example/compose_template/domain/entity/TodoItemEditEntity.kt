package com.example.compose_template.domain.entity

import java.util.Date

data class TodoItemEditEntity(
    val id: Int,
    val name: String,
    val desc: String,
    val lastUpdateDate: Date,
    val isFavorite: Boolean,
)