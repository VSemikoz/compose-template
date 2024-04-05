package com.example.compose_template.domain.repository

import com.example.compose_template.domain.entity.person.person.PersonResultEntry


interface IPersonRepository {
    suspend fun getPersonList(count: Int = DEFAULT_PERSON_COUNT): PersonResultEntry

    private companion object {
        const val DEFAULT_PERSON_COUNT = 20
    }
}