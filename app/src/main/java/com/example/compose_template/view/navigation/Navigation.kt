package com.example.compose_template.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.example.compose_template.view.navigation.transfer.navType.TodoMinimalNavType
import com.example.compose_template.view.screen.addEditNewSheet.AddEditTodoSheet
import com.example.compose_template.view.screen.settings.SettingsScreen
import com.example.compose_template.view.screen.todoList.TodoListScreen

@Composable
fun TemplateNavHost(
    modifier: Modifier,
    globalVMStoreOwner: ViewModelStoreOwner,
) {

    val navController = getNavigator()
    NavHost(
        navController = navController,
        startDestination = Screen.TodoList.route,
        modifier = modifier
    ) {
        composable(route = Screen.TodoList.route) {
            TodoListScreen()
        }

        composable(route = Screen.Settings.route) {
            CompositionLocalProvider(
                LocalViewModelStoreOwner provides globalVMStoreOwner
            ) {
                SettingsScreen()
            }

        }

        dialog(route = Screen.AddEditTodo.route, arguments = Screen.AddEditTodo.arguments) { navBackStackEntry ->
            val todo = navBackStackEntry.arguments?.getString(Screen.AddEditTodo.ITEM)?.let { item ->
                TodoMinimalNavType().fromJsonParse(item)
            }
            AddEditTodoSheet(
                initialEditTodo = todo
            )
        }
    }
}