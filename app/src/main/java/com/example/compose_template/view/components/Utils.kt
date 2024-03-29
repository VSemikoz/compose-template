package com.example.compose_template.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode


@Composable
fun isPreview(): Boolean = LocalInspectionMode.current

@Composable
fun isNotPreview(): Boolean = !isPreview()

