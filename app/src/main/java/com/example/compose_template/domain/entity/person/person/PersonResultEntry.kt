package com.example.compose_template.domain.entity.person.person

import PersonEntry

data class PersonResultEntry(
    val results: List<PersonEntry>,
    val info: PersonResultInfoEntry,
)