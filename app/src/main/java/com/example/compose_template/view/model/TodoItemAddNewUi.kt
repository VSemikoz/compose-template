package com.example.compose_template.view.model

import java.util.Date

data class TodoItemAddNewUi(
    val name: String,
    val desc: String,
    val creationDate: Date?,
    val nameIsEmptyError: Boolean = false,
    val exitScreen: Boolean = false,
)