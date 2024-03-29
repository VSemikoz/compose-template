package com.example.compose_template.view.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_template.view.components.BottomAppBar
import com.example.compose_template.view.navigation.TemplateNavHost
import com.example.compose_template.view.navigation.currentScreen
import com.example.compose_template.view.theme.TemplateTheme

@Composable
fun TemplateApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentScreen = currentDestination.currentScreen()
    TemplateTheme {
        Scaffold(
            modifier,
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier,
                    navController = navController,
                    currentScreen = currentScreen,
                )
            },
        ) { paddingValue ->
            TemplateNavHost(
                modifier = Modifier.padding(paddingValue),
                navController = navController,
            )
        }
    }
}
