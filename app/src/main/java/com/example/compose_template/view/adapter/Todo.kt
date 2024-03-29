package com.example.compose_template.view.adapter

import com.example.compose_template.domain.entity.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.TodoItemEntity
import com.example.compose_template.view.model.TodoItemAddNewUi
import com.example.compose_template.view.model.TodoItemMinimalUi
import java.time.Instant
import java.util.Date

fun TodoItemEntity.toMinimalUi(): TodoItemMinimalUi {
    return TodoItemMinimalUi(
        id = this.id,
        name = this.name,
        desc = this.desc,
        lastUpdateDate = this.lastUpdateDate,
        isFavorite = this.isFavorite,
    )
}

fun TodoItemAddNewUi.toDomain(): TodoItemAddNewEntity {
    return TodoItemAddNewEntity(
        name = this.name,
        desc = this.desc,
        creationDate = this.creationDate ?: Date.from(Instant.now()),
    )
}
