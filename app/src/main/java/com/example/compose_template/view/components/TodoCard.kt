@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose_template.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_template.R
import com.example.compose_template.view.model.TodoItemMinimalUi
import com.example.compose_template.view.theme.TemplateColors
import com.example.compose_template.view.theme.TemplateTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale


@Composable
fun TodoCard(
    modifier: Modifier = Modifier,
    item: TodoItemMinimalUi,
    handleFavorite: (item: TodoItemMinimalUi) -> Unit = {},
    handleRemove: (item: TodoItemMinimalUi) -> Unit = {},
    onClick: (item: TodoItemMinimalUi) -> Unit = {},
) {

    var delete by remember { mutableStateOf(false) }
    var addFavorite by remember { mutableStateOf(false) }

    if (delete) {
        handleRemove(item)
        delete = false
    }
    if (addFavorite) {
        handleFavorite(item)
        addFavorite = false
    }

    var performed by remember { mutableStateOf(false) }
    val dismissState = rememberDismissState(
        confirmValueChange = {
            when (it) {
                DismissValue.DismissedToEnd -> {
                    addFavorite = true
                }

                DismissValue.DismissedToStart -> {
                    delete = true
                }

                DismissValue.Default -> {
                    return@rememberDismissState false
                }
            }
            performed = true
            it in listOf(DismissValue.DismissedToStart, DismissValue.DismissedToStart)
        },
        positionalThreshold = { 200.dp.toPx() }
    )
    SwipeToDismiss(
        state = dismissState,
        modifier = modifier,
        background = { DismissBackground(dismissState) },
        dismissContent = {
            TodoCardForeground(
                modifier = Modifier,
                item = item,
                handleFavorite = handleFavorite,
                onClick = onClick,
            )
        }
    )

    LaunchedEffect(performed) {
        if (performed) {
            delay(400)
            dismissState.reset()
            performed = false
        }
    }

}

@Composable
private fun TodoCardForeground(
    modifier: Modifier = Modifier,
    item: TodoItemMinimalUi,
    handleFavorite: (item: TodoItemMinimalUi) -> Unit = {},
    onClick: (item: TodoItemMinimalUi) -> Unit = {},
) {
    val simpleDate = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
    val lastUpdateDateText = simpleDate.format(item.lastUpdateDate)
    Card(
        modifier
            .fillMaxWidth()
            .clickable { onClick(item) }
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.primary)
                .height(IntrinsicSize.Min)
                .padding(8.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                Modifier.weight(1f),
            ) {
                Text(
                    text = item.name,
                    maxLines = 3,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = item.desc,
                    maxLines = 3,
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            Column(Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { handleFavorite(item) },
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally),
                ) {
                    if (item.isFavorite) Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = stringResource(R.string.remove_favorite),
                        tint = TemplateColors.Red,
                    )
                    else Icon(
                        imageVector = Icons.TwoTone.Favorite,
                        contentDescription = stringResource(R.string.add_to_favorite),
                        tint = TemplateColors.Red,
                    )
                }
                Text(
                    text = stringResource(R.string.last_updated, lastUpdateDateText),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}


@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> TemplateColors.Yellow
        DismissDirection.EndToStart -> TemplateColors.Red
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.StartToEnd) Icon(
            Icons.TwoTone.Star,
            tint = TemplateColors.Black,
            contentDescription = stringResource(R.string.add_to_favorite)
        )
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart) Icon(
            Icons.Default.Delete,
            tint = TemplateColors.Black,
            contentDescription = stringResource(R.string.delete)
        )
    }
}

@Preview
@Composable
private fun TodoCardPreview() {
    TemplateTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TodoCard(
                item = previewCardMock,
            )
            TodoCard(
                item = previewCardMock.copy(isFavorite = false),
            )
        }
    }
}

val previewCardMock = TodoItemMinimalUi(
    id = -1,
    name = "Test Name",
    desc = "test description".repeat(10),
    lastUpdateDate = Date.from(Instant.now()),
    isFavorite = true,
)