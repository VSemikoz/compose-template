package com.example.compose_template.domain.usecase

import com.example.compose_template.domain.entity.LanguageState
import com.example.compose_template.domain.entity.SettingsEntity
import com.example.compose_template.domain.entity.ThemeState
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