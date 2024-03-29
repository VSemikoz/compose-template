package com.example.compose_template.view.model

import java.util.Date

data class TodoItemMinimalUi(
    val id: Int,
    val name: String,
    val desc: String,
    val lastUpdateDate: Date,
    val isFavorite: Boolean,
)

