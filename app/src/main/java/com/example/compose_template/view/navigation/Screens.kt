package com.example.compose_template.view.navigation

import androidx.navigation.navArgument
import com.example.compose_template.view.model.TodoItemMinimalUi
import com.example.compose_template.view.navigation.transfer.navType.TodoMinimalNavType

sealed class Screen(
    val route: String,
    val isBottomBarVisible: Boolean = false,
) {
    data object TodoList : Screen(route = "todoList", isBottomBarVisible = true)
    data object Settings : Screen(route = "settings", isBottomBarVisible = true)
    class AddEditTodo : Screen(route = route, isBottomBarVisible = true) {
        companion object {
            private const val ROUTE = "artist"
            const val ITEM = "item"
            const val route = "$ROUTE/{$ITEM}"
            val arguments = listOf(
                navArgument(ITEM) { type = TodoMinimalNavType() }
            )

            fun navigate(todo: TodoItemMinimalUi?): String {

                return "$ROUTE/${TodoMinimalNavType().getJsonParse(todo)}"
            }
        }
    }

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route) {
                TodoList.route -> TodoList
                Settings.route -> Settings
                AddEditTodo.route -> AddEditTodo()
//                else -> throw IllegalArgumentException("Route $route is not recognized.")
                else -> TodoList//TODO make smth
            }
    }
}