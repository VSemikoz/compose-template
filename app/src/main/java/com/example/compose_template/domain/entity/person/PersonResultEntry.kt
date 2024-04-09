package com.example.compose_template.domain.entity.person

data class PersonResultEntry(
    val results: List<PersonEntry>,
    val info: PersonResultInfoEntry,
)