package com.example.compose_template.view.screen.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose_template.R
import com.example.compose_template.domain.entity.settings.LanguageState
import com.example.compose_template.domain.entity.settings.ThemeState
import com.example.compose_template.view.app.GlobalVM
import com.example.compose_template.view.components.PersonCard
import com.example.compose_template.view.components.findActivity
import com.example.compose_template.view.model.PersonItemUi
import com.example.compose_template.view.theme.TemplateTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    val globalVM: GlobalVM = hiltViewModel()
    val settingsVM: SettingsVM = hiltViewModel()
    val personPager = settingsVM.state.getIfSuccess()?.collectAsLazyPagingItems()

    Surface(modifier) {
        Column(
            Modifier.fillMaxWidth(),
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

            if (personPager != null) PersonList(personsPager = personPager)
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


@Composable
fun PersonList(
    modifier: Modifier = Modifier,
    personsPager: LazyPagingItems<PersonItemUi>
) {
    LazyColumn(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        items(personsPager.itemCount) {
            PersonCard(item = checkNotNull(personsPager[it]), modifier = Modifier.padding(4.dp))
        }

        personsPager.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }
            }
        }
    }


}

@Preview
@Composable
private fun SettingsScreenPreview() {
    TemplateTheme {
        SettingsScreen()
    }
}