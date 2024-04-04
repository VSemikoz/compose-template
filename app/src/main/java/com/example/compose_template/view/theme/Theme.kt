package com.example.compose_template.view.theme

import android.graphics.Color.TRANSPARENT
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.example.compose_template.view.components.isNotPreview

private val DarkColorScheme = darkColorScheme(
    primary = TemplateColors.DarkBlue,
    secondary = TemplateColors.Blue,
    tertiary = TemplateColors.Gray,
    background = TemplateColors.Black,
    surface = TemplateColors.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = TemplateColors.LightBlue,
    secondary = TemplateColors.Blue,
    tertiary = TemplateColors.Gray,
    background = TemplateColors.White,
    surface = TemplateColors.White,
)

@Composable
fun TemplateTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    if (isNotPreview()) TransparentSystemBars(isDarkTheme = isDarkTheme)
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
private fun TransparentSystemBars(isDarkTheme: Boolean) {
    val activity = LocalContext.current as ComponentActivity
    val style = SystemBarStyle.auto(TRANSPARENT, TRANSPARENT) { isDarkTheme }
    SideEffect {
        activity.enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
    }
}