package com.example.compose_template.domain.usecase

import com.example.compose_template.domain.entity.person.person.PersonResultEntry
import com.example.compose_template.domain.repository.IPersonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonUseCase @Inject constructor(private val personRepository: IPersonRepository) {
    suspend fun getPersonList(count: Int? = null): PersonResultEntry {
        if (count == null) {
            return personRepository.getPersonList()
        }
        return personRepository.getPersonList(count = count)
    }

}