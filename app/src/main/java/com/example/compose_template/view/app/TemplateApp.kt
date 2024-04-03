package com.example.compose_template.view.app

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_template.domain.entity.ThemeState
import com.example.compose_template.view.components.BottomAppBar
import com.example.compose_template.view.navigation.TemplateNavHost
import com.example.compose_template.view.navigation.currentScreen
import com.example.compose_template.view.theme.TemplateTheme

@Composable
fun TemplateApp(
    modifier: Modifier = Modifier,
    localNavController: ProvidableCompositionLocal<NavHostController>
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentScreen = currentDestination.currentScreen()

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    val themeVM: GlobalVM = hiltViewModel(viewModelStoreOwner)

    val isDark = themeVM.state.getIfSuccess()?.theme == ThemeState.Dark
    val locale = themeVM.state.getIfSuccess()?.language?.locale ?: "en"
    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))

    TemplateTheme(
        isDarkTheme = isDark
    ) {
        CompositionLocalProvider(localNavController provides navController) {
            Scaffold(
                modifier,
                bottomBar = {
                    BottomAppBar(
                        modifier = Modifier,
                        currentScreen = currentScreen,
                    )
                },
            ) { paddingValue ->
                TemplateNavHost(
                    modifier = Modifier.padding(paddingValue),
                    globalVMStoreOwner = viewModelStoreOwner,
                )
            }
        }

    }
}
