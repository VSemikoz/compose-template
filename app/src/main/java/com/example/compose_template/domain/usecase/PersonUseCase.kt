package com.example.compose_template.domain.usecase

import PersonEntry
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.compose_template.data.repository.paginSource.PersonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonUseCase @Inject constructor(private val personPagingSource: PersonPagingSource) {
    fun getPersonList(): Flow<PagingData<PersonEntry>> {

        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PERSON_COUNT, prefetchDistance = DEFAULT_PREFETCH_DISTANCE),
            pagingSourceFactory = {
                personPagingSource
            }
        ).flow
    }

    private companion object {
        const val DEFAULT_PERSON_COUNT = 20
        const val DEFAULT_PREFETCH_DISTANCE = 1
    }
}