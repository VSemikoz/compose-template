package com.example.compose_template.view.screen.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_template.R
import com.example.compose_template.domain.entity.LanguageState
import com.example.compose_template.domain.entity.ThemeState
import com.example.compose_template.view.app.GlobalVM
import com.example.compose_template.view.components.findActivity
import com.example.compose_template.view.theme.TemplateTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    val globalVM: GlobalVM = hiltViewModel()

    Surface(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ThemeSwitcher(
                themeState = globalVM.state.getIfSuccess()?.theme,
                invertTheme = globalVM::invertTheme
            )

            LangSwitcher(
                languageState = globalVM.state.getIfSuccess()?.language,
                invertLang = globalVM::invertLanguage
            )
        }
    }
}

@Composable
fun ThemeSwitcher(
    modifier: Modifier = Modifier,
    themeState: ThemeState?,
    invertTheme: () -> Unit
) {


    Row(modifier) {
        Text(text = stringResource(R.string.change_theme))

        Switch(
            checked = themeState == ThemeState.Light,
            onCheckedChange = { invertTheme() },
        )
    }
}

@Composable
fun LangSwitcher(
    modifier: Modifier = Modifier,
    languageState: LanguageState?,
    invertLang: () -> LanguageState?
) {
    val context = LocalContext.current

    Row(modifier) {
        Text(text = stringResource(R.string.change_language))

        Switch(
            checked = languageState == LanguageState.En,
            onCheckedChange = {
                val currentLang = invertLang() ?: return@Switch

                context.findActivity()?.runOnUiThread {
                    val appLocale = LocaleListCompat.forLanguageTags(currentLang.locale)
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
            },
        )
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    TemplateTheme {
        SettingsScreen()
    }
}