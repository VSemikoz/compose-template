package com.example.compose_template.view.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_template.domain.entity.LanguageState
import com.example.compose_template.domain.entity.ThemeState
import com.example.compose_template.domain.usecase.SettingsUseCase
import com.example.compose_template.view.model.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration
import javax.inject.Inject


@HiltViewModel
class GlobalVM
@Inject constructor(
    private val settingsUseCase: SettingsUseCase,
) : ViewModel() {

    var state by mutableStateOf<UiState<GlobalState>>(UiState.Loading)
        private set

    init {
        viewModelScope.launch {
            delay(Duration.ofSeconds(1))
            val savedSettings = settingsUseCase.getSettings()
            val themeToSet = savedSettings.themeState ?: defaultTheme
            val langToSet = savedSettings.languageState ?: defaultLang
            state = UiState.Success(
                GlobalState(
                    theme = themeToSet,
                    language = langToSet
                )
            )
        }
    }


    fun invertTheme() {
        val prevLang = state.getIfSuccess()?.language ?: defaultLang
        val prevTheme = state.getIfSuccess()?.theme ?: return

        state = UiState.Success(
            GlobalState(
                theme = when (prevTheme) {
                    ThemeState.Dark -> ThemeState.Light
                    ThemeState.Light -> ThemeState.Dark
                },
                language = prevLang,
            )
        )

        val newTheme = state.getIfSuccess()?.theme ?: return
        viewModelScope.launch {
            settingsUseCase.updateTheme(newTheme)
        }
    }

    fun invertLanguage(): LanguageState? {
        val prevTheme = state.getIfSuccess()?.theme ?: defaultTheme
        val prevLang = state.getIfSuccess()?.language ?: return null

        state = UiState.Success(
            GlobalState(
                language = when (prevLang) {
                    LanguageState.Ru -> LanguageState.En
                    LanguageState.En -> LanguageState.Ru
                },
                theme = prevTheme,
            )
        )

        val newLang = state.getIfSuccess()?.language ?: return null
        viewModelScope.launch {
            settingsUseCase.updateLang(newLang)
        }
        return state.getIfSuccess()?.language
    }


    private companion object {
        val defaultTheme = ThemeState.Light
        val defaultLang = LanguageState.Ru
    }

}

data class GlobalState(
    val theme: ThemeState,
    val language: LanguageState,
)