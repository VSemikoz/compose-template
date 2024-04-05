package com.example.compose_template.view.screen.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_template.domain.usecase.PersonUseCase
import com.example.compose_template.view.adapter.toUi
import com.example.compose_template.view.model.PersonItemUi
import com.example.compose_template.view.model.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM
@Inject constructor(
    private val personUseCase: PersonUseCase,
) : ViewModel() {

    var state by mutableStateOf<UiState<List<PersonItemUi>>>(UiState.Loading)
        private set

    init {
        viewModelScope.launch {
            val list = personUseCase.getPersonList()
            state = UiState.Success(list.results.map { it.toUi() })
        }
    }

}
