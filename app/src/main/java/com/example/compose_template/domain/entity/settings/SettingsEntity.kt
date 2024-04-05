package com.example.compose_template.domain.entity.settings


data class SettingsEntity(
    val themeState: ThemeState?,
    val languageState: LanguageState?,
)

object DefaultSettings {
    const val LIGHT_THEME = "0"
    const val DARK_THEME = "1"
    const val EN_LANG = "en"
    const val RU_LANG = "ru"
}

enum class ThemeState(val index: String) {
    Light(DefaultSettings.LIGHT_THEME),
    Dark(DefaultSettings.DARK_THEME),
}

enum class LanguageState(val locale: String) {
    Ru(DefaultSettings.RU_LANG), En(DefaultSettings.EN_LANG)
}
