package com.example.compose_template.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose_template.view.navigation.Screen
import com.example.compose_template.view.navigation.getNavigator
import com.example.compose_template.view.navigation.hasRouteInHierarchy
import com.example.compose_template.view.navigation.popAndNavigate
import com.example.compose_template.view.theme.TemplateColors

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    currentScreen: Screen,
) {
    val navigator = getNavigator()
    val backStackEntry by navigator.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    Column(modifier) {
        AnimatedVisibility(visible = currentScreen.isBottomBarVisible) {
            BottomAppBarBuilder(
                currentDestination = currentDestination,
                navigate = navigator::popAndNavigate,//TODO maybe get another method
            )
        }

    }
}

@Composable
fun BottomAppBarBuilder(
    currentDestination: NavDestination?,
    navigate: (String) -> Unit,
) {
    val items = BottomNavigation.entries
    BottomNavigation {
        items.forEach { item ->
            val isSelected = currentDestination.hasRouteInHierarchy(item.route)
            BottomNavigationItem(
                selected = isSelected,
                onClick = { if (!isSelected) navigate(item.route) },
                item = item,
            )
        }
    }
}

@Composable
fun BottomNavigation(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(color = TemplateColors.White)
            .navigationBarsPadding()
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    Pair(0f, TemplateColors.White),
                    Pair(1f, Color.Transparent)
                ),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    item: BottomNavigation,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .weight(1f)
            .padding(top = 12.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val activeColor = MaterialTheme.colorScheme.primary
        val inactiveColor = MaterialTheme.colorScheme.tertiary
        Box {
            Icon(
                painter = painterResource(id = if (selected) item.selectedIconResId else item.iconResId),
                contentDescription = stringResource(id = item.descriptionResId),
                modifier = Modifier
                    .size(32.dp)
                    .align(alignment = Alignment.Center),
                tint = if (selected) activeColor else inactiveColor,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = item.labelResId),
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) activeColor else inactiveColor,
        )
    }
}
