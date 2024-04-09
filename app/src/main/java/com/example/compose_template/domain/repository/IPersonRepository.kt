package com.example.compose_template.domain.repository

import com.example.compose_template.domain.entity.person.PersonResultEntry


interface IPersonRepository {
    suspend fun getPersonList(count: Int, page: Int): PersonResultEntry
}