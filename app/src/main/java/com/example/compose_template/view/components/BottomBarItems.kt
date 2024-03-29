package com.example.compose_template.view.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.compose_template.R
import com.example.compose_template.view.navigation.Screen


enum class BottomNavigation(
    val route: String,
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int,
    @StringRes val labelResId: Int,
    @StringRes val descriptionResId: Int
) {
    TodoList(
        route = Screen.TodoList.route,
        iconResId = R.drawable.ic_home,
        selectedIconResId = R.drawable.ic_home_selected,
        labelResId = R.string.navigation_todo_list_label,
        descriptionResId = R.string.navigation_todo_list_label
    ),
    Settings(
        route = Screen.Settings.route,
        iconResId = R.drawable.ic_info,
        selectedIconResId = R.drawable.ic_info_selected,
        labelResId = R.string.navigation_settings_label,
        descriptionResId = R.string.navigation_settings_label
    ),
}