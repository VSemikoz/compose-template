package com.example.compose_template.view.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.compose_template.domain.usecase.PersonUseCase
import com.example.compose_template.view.adapter.toUi
import com.example.compose_template.view.model.PersonItemUi
import com.example.compose_template.view.model.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM
@Inject constructor(
    private val personUseCase: PersonUseCase,
) : ViewModel() {

    var state: UiState<MutableStateFlow<PagingData<PersonItemUi>>> = UiState.Loading
        private set

    init {
        viewModelScope.launch {
            personUseCase.getPersonList()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { page ->
                    state = UiState.Success(
                        MutableStateFlow(
                            page.map { it.toUi() }
                        )
                    )
                }
        }
    }

}
