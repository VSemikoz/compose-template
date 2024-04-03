package com.example.compose_template.view.app

import com.example.compose_template.domain.entity.LanguageState
import com.example.compose_template.domain.entity.ThemeState

data class GlobalState(
    val theme: ThemeState,
    val language: LanguageState,
)