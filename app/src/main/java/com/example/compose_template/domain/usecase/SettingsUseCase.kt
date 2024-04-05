package com.example.compose_template.domain.usecase

import com.example.compose_template.domain.entity.settings.LanguageState
import com.example.compose_template.domain.entity.settings.SettingsEntity
import com.example.compose_template.domain.entity.settings.ThemeState
import com.example.compose_template.domain.repository.ISettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsUseCase @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {
    fun updateTheme(themeState: ThemeState) = settingsRepository.updateTheme(themeState)

    fun updateLang(languageState: LanguageState) = settingsRepository.updateLang(languageState)

    fun getSettings(): SettingsEntity = settingsRepository.getSettings()
}