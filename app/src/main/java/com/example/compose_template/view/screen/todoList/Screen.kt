package com.example.compose_template.view.screen.todoList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_template.view.components.TodoCard
import com.example.compose_template.view.components.previewCardMock
import com.example.compose_template.view.model.TodoItemMinimalUi
import com.example.compose_template.view.navigation.Screen
import com.example.compose_template.view.navigation.getNavigator

@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    todoList: List<TodoItemMinimalUi> = emptyList(),
) {

    val vm: TodoListVM = hiltViewModel()
    val list = vm.state.getIfSuccess() ?: todoList
    val navigator = getNavigator()

    Scaffold(
        modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.navigate(Screen.AddEditTodo.navigate(null)) },
            ) { Icon(imageVector = Icons.Filled.Add, contentDescription = null) }
        },
    ) { paddingValues ->
        Surface {
            LazyColumn(
                Modifier
                    .padding(paddingValues)
                    .padding(4.dp, 4.dp)
            ) {
                items(list.size) {
                    val item = list[it]
                    TodoCard(
                        modifier = Modifier.padding(4.dp),
                        item = item,
                        onClick = {
                            navigator.navigate(Screen.AddEditTodo.navigate(item))
                        },
                        handleRemove = vm::deleteTodo,
                        handleFavorite = vm::handleFavorite,
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun TodoListScreenPreview() {
    TodoListScreen(todoList = buildList {
        repeat(10) { add(previewCardMock) }
    })
}