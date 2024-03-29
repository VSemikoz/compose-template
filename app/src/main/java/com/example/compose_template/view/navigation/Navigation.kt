package com.example.compose_template.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.example.compose_template.view.navigation.transfer.navType.TodoMinimalNavType
import com.example.compose_template.view.screen.addEditNewSheet.AddEditTodoSheet
import com.example.compose_template.view.screen.settings.SettingsScreen
import com.example.compose_template.view.screen.todoList.TodoListScreen

@Composable
fun TemplateNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.TodoList.route,
        modifier = modifier
    ) {
        composable(route = Screen.TodoList.route) {
            TodoListScreen(navigate = navController::navigate)
        }

        composable(route = Screen.Settings.route) {
            SettingsScreen(navigate = navController::navigate)
        }

        dialog(route = Screen.AddEditTodo.route, arguments = Screen.AddEditTodo.arguments) { navBackStackEntry ->
            val todo = navBackStackEntry.arguments?.getString(Screen.AddEditTodo.ITEM)?.let { item ->
                TodoMinimalNavType().fromJsonParse(item)
            }
            AddEditTodoSheet(
                onDismissRequest = navController::popBackStack,
                initialEditTodo = todo
            )
        }
    }
}