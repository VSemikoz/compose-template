package com.example.compose_template.data.repository.paginSource

import com.example.compose_template.domain.entity.person.PersonEntry
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.compose_template.domain.repository.IPersonRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonPagingSource @Inject constructor(
    private val personRepository: IPersonRepository,
) : PagingSource<Int, PersonEntry>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonEntry> {
        return try {

            val currentPage = params.key ?: 1
            val persons = personRepository.getPersonList(
                page = currentPage,
                count = params.loadSize,
            )
            LoadResult.Page(
                data = persons.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (persons.results.isEmpty()) null else persons.info.page.toInt() + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PersonEntry>): Int? {
        return state.anchorPosition
    }
}