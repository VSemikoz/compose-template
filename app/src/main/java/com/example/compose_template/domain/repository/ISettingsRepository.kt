package com.example.compose_template.domain.repository

import com.example.compose_template.domain.entity.LanguageState
import com.example.compose_template.domain.entity.SettingsEntity
import com.example.compose_template.domain.entity.ThemeState

interface ISettingsRepository {

    fun updateTheme(themeState: ThemeState)
    fun updateLang(languageState: LanguageState)
    fun getSettings(): SettingsEntity
}