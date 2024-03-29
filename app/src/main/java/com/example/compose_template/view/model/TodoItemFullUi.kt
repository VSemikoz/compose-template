package com.example.compose_template.view.model

import java.util.Date

data class TodoItemFullUi(
    val id: Int,
    val name: String,
    val desc: String,
    val creationDate: Date,
    val lastUpdateDate: Date,
    val isFavorite: Boolean,
)