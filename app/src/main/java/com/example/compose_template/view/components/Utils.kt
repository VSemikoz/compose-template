package com.example.compose_template.view.components

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun isPreview(): Boolean = LocalInspectionMode.current

@Composable
fun isNotPreview(): Boolean = !isPreview()

fun Context.findActivity() : Activity? = when(this){
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}