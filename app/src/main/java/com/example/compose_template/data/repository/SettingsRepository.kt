package com.example.compose_template.data.repository

import com.example.compose_template.data.sharedPref.PreferencesManager
import com.example.compose_template.domain.entity.settings.DefaultSettings
import com.example.compose_template.domain.entity.settings.LanguageState
import com.example.compose_template.domain.entity.settings.SettingsEntity
import com.example.compose_template.domain.entity.settings.ThemeState
import com.example.compose_template.domain.repository.ISettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ISettingsRepository {
    override fun updateTheme(themeState: ThemeState) {
        preferencesManager.saveData(THEME_KEY, themeState.index)
    }

    override fun updateLang(languageState: LanguageState) {
        preferencesManager.saveData(LANG_KEY, languageState.locale)
    }

    override fun getSettings(): SettingsEntity {
        val themeIndex = preferencesManager.getData(THEME_KEY, DefaultSettings.LIGHT_THEME)
        val langLocale = preferencesManager.getData(LANG_KEY, DefaultSettings.EN_LANG)

        val themeState: ThemeState? = when (themeIndex) {
            DefaultSettings.LIGHT_THEME -> ThemeState.Light
            DefaultSettings.DARK_THEME -> ThemeState.Dark
            else -> null
        }
        val languageState: LanguageState? = when (langLocale) {
            DefaultSettings.EN_LANG -> LanguageState.En
            DefaultSettings.RU_LANG -> LanguageState.Ru
            else -> null
        }

        return SettingsEntity(themeState, languageState)
    }


    private companion object {
        const val THEME_KEY = "THEME_KEY"
        const val LANG_KEY = "LANG_KEY"
    }
}