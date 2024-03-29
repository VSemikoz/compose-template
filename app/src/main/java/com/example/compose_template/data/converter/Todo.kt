package com.example.compose_template.data.converter

import com.example.compose_template.data.database.model.TodoData
import com.example.compose_template.domain.entity.TodoItemAddNewEntity
import com.example.compose_template.domain.entity.TodoItemEntity
import java.time.Instant
import java.util.Date


fun TodoData.intoDomain(): TodoItemEntity {
    return TodoItemEntity(
        id = id,
        name = name,
        desc = desc,
        creationDate = creationDate,
        lastUpdateDate = lastUpdateDate,
        isFavorite = isFavorite,
    )
}

fun TodoItemEntity.intoData(): TodoData {
    return TodoData(
        id = id,
        name = name,
        desc = desc,
        creationDate = creationDate,
        lastUpdateDate = lastUpdateDate,
        isFavorite = isFavorite,
    )
}

fun TodoItemAddNewEntity.intoData(): TodoData {
    return TodoData(
        name = name,
        desc = desc,
        creationDate = creationDate,
        lastUpdateDate = Date.from(Instant.now()),
        isFavorite = false,
    )
}