package com.example.compose_template.view.screen.todoList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_template.domain.usecase.TodoUseCase
import com.example.compose_template.view.adapter.toMinimalUi
import com.example.compose_template.view.model.TodoItemMinimalUi
import com.example.compose_template.view.model.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoListVM
@Inject constructor(
    private val todoUseCase: TodoUseCase,
) : ViewModel() {
    var state by mutableStateOf<UiState<List<TodoItemMinimalUi>>>(UiState.Loading)
        private set

    init {
        viewModelScope.launch {
            todoUseCase.subscribeAll().collect { todoList ->
                val mappedList = todoList.map { it.toMinimalUi() }
                state = UiState.Success(mappedList)
            }
        }
    }


    fun deleteTodo(todo: TodoItemMinimalUi) {
        viewModelScope.launch {
            todoUseCase.deleteById(todo.id)
        }
    }

    fun handleFavorite(todo: TodoItemMinimalUi) {
        val currentState = todo.isFavorite
        viewModelScope.launch {
            todoUseCase.setFavorite(todo.id, !currentState)
        }
    }
}