package com.example.compose_template.view.model.common

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val uiError: UiError) : UiState<Nothing>

    fun runIfLoading(block: () -> Unit) {
        if (this is Loading) block()
    }

    fun runIfSuccess(block: (T) -> Unit) {
        if (this is Success) block(data)
    }

    fun getIfSuccess(): T? {
        return (this as? Success)?.data
    }

    fun <O> getIfSuccess(block: T.() -> O): O? {
        return (this as? Success)?.let { block(data) }
    }
}