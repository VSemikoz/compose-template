package com.example.compose_template.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.compose_template.MainActivity
import com.example.compose_template.view.components.findActivity
import com.example.compose_template.view.model.common.UiError

fun NavHostController.startDestinationId() = graph.findStartDestination().id

fun NavHostController.popAndNavigate(route: String) = navigate(route) {
    popUpTo(currentBackStackEntry?.destination?.route ?: throw UiError.RouteIsNull) {
        inclusive = true
    }
}

fun NavHostController.popAndNavigate(popUpRoute: String, route: String) = navigate(route) {
    popUpTo(popUpRoute)
}

fun NavHostController.clearAndNavigate(route: String) = navigate(route) {
    popUpTo(0)
}

fun NavDestination?.currentScreen() = Screen.fromRoute(this?.route)

fun NavDestination?.isNotStartDestination() = this?.route != this?.parent?.startDestinationRoute

fun NavDestination?.hasRouteInHierarchy(route: String) =
    this?.hierarchy?.any { it.route == route } == true


@Composable
fun getNavigator(): NavHostController {
    return (LocalContext.current.findActivity() as MainActivity).localNavController.current
}