package com.example.compose_template.data.repository

import com.example.compose_template.data.converter.PersonConverter
import com.example.compose_template.data.network.PersonApi
import com.example.compose_template.domain.entity.person.person.PersonResultEntry
import com.example.compose_template.domain.repository.IPersonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personApi: PersonApi, private val personConverter: PersonConverter
) : IPersonRepository {
    override suspend fun getPersonList(count: Int, page: Int): PersonResultEntry {
        return personConverter.intoDomain(personApi.getPersonList(count, page))
    }
}
