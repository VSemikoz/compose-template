package com.example.compose_template.view.screen.addEditNewSheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_template.domain.usecase.TodoUseCase
import com.example.compose_template.view.adapter.toDomain
import com.example.compose_template.view.model.TodoItemAddNewUi
import com.example.compose_template.view.model.TodoItemMinimalUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

@HiltViewModel(assistedFactory = AddEditVM.DetailViewModelFactory::class)
class AddEditVM @AssistedInject constructor(
    private val todoUseCase: TodoUseCase,
    @Assisted val initialEditTodo: TodoItemMinimalUi?,
) : ViewModel() {
    var state by mutableStateOf(TodoItemAddNewUi("", "", null, false))
        private set

    val confirmStatus = if (initialEditTodo == null) ConfirmStatus.Add else ConfirmStatus.Edit

    init {
        if (initialEditTodo != null) {
            setName(initialEditTodo.name)
            setDesc(initialEditTodo.desc)
        }
    }

    @AssistedFactory
    interface DetailViewModelFactory {
        fun create(initialEditTodo: TodoItemMinimalUi?): AddEditVM
    }


    fun setName(name: String) {
        state = state.copy(name = name, nameIsEmptyError = false)
    }

    fun setDesc(desc: String) {
        state = state.copy(desc = desc, nameIsEmptyError = false)
    }

    fun confirm() {
        if (initialEditTodo == null) {
            confirmAdd()
        } else {
            confirmEdit()
        }
    }

    private fun confirmAdd() {
        state = state.copy(creationDate = Date.from(Instant.now()))
        if (state.name.isEmpty()) {
            state = state.copy(nameIsEmptyError = true)
            return
        }

        viewModelScope.launch {
            todoUseCase.addNew(state.toDomain())
        }
        state = state.copy(exitScreen = true)
    }


    private fun confirmEdit() {
        state = state.copy(creationDate = Date.from(Instant.now()))
        if (state.name.isEmpty()) {
            state = state.copy(nameIsEmptyError = true)
            return
        }
        val initTodo = initialEditTodo
        if (initTodo == null) {
            confirmAdd()
            return
        }

        viewModelScope.launch {
            val fullTodo = todoUseCase.getById(initTodo.id)
            val edited = fullTodo.copy(name = state.name, desc = state.desc)
            todoUseCase.update(edited)
        }
        state = state.copy(exitScreen = true)
    }

}