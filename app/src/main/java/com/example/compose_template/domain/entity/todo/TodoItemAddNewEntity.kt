package com.example.compose_template.domain.entity.todo

import java.util.Date

data class TodoItemAddNewEntity(
    val name: String,
    val desc: String,
    val creationDate: Date,
)