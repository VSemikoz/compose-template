package com.example.compose_template.domain.repository

import com.example.compose_template.domain.entity.settings.LanguageState
import com.example.compose_template.domain.entity.settings.SettingsEntity
import com.example.compose_template.domain.entity.settings.ThemeState

interface ISettingsRepository {

    fun updateTheme(themeState: ThemeState)
    fun updateLang(languageState: LanguageState)
    fun getSettings(): SettingsEntity
}