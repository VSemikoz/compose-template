package com.example.compose_template.view.screen.addEditNewSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_template.R
import com.example.compose_template.view.model.TodoItemMinimalUi
import com.example.compose_template.view.navigation.getNavigator
import com.example.compose_template.view.theme.TemplateTheme
import kotlinx.coroutines.time.delay
import java.time.Duration

@Composable
fun AddEditTodoSheet(
    modifier: Modifier = Modifier,
    initialEditTodo: TodoItemMinimalUi? = null,
) {
    val vm = hiltViewModel<AddEditVM, AddEditVM.DetailViewModelFactory> {
        it.create(initialEditTodo)
    }
    val navigator = getNavigator()

    val state = vm.state
    if (state.exitScreen) navigator.popBackStack()
    val nameFocusRequester = remember { FocusRequester() }
    val descFocusRequester = remember { FocusRequester() }

    Dialog(onDismissRequest = { navigator.popBackStack() }) {
        Card(modifier = modifier) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Spacer(Modifier.height(8.dp))
                TextField(value = state.name,
                    placeholder = { Text(stringResource(R.string.input_name)) },
                    isError = state.nameIsEmptyError,
                    onValueChange = { vm.setName(it) },
                    modifier = Modifier.focusRequester(nameFocusRequester),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { descFocusRequester.requestFocus() })
                )
                Spacer(Modifier.height(8.dp))
                TextField(value = state.desc,
                    placeholder = { Text(stringResource(R.string.input_desc)) },
                    onValueChange = { vm.setDesc(it) },
                    singleLine = true,
                    modifier = Modifier.focusRequester(descFocusRequester),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { vm.confirm() })
                )

                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.Right
                ) {
                    Text(
                        stringResource(R.string.cancel),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navigator.popBackStack() },
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        when (vm.confirmStatus) {
                            ConfirmStatus.Add -> stringResource(R.string.add_new)
                            ConfirmStatus.Edit -> stringResource(R.string.confirm_edit)
                        },
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { vm.confirm() },
                    )
                }

            }
        }
    }

    LaunchedEffect(Unit) {
        delay(Duration.ofMillis(400))
        nameFocusRequester.requestFocus()
    }

    LaunchedEffect(state.nameIsEmptyError) {
        if (!state.nameIsEmptyError) return@LaunchedEffect
        delay(Duration.ofMillis(400))
        nameFocusRequester.requestFocus()
    }

}


@Preview
@Composable
private fun AddNewTodoSheetPreview() {
    TemplateTheme {
        AddEditTodoSheet()
    }
}