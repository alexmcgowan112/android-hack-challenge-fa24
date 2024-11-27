package com.example.a6starter.ui.screens.main

import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: Painter
)

@Serializable
sealed class Screen {
    @Serializable
    data object UploadScreen : Screen()

    @Serializable
    data object InfoScreen : Screen()

    @Serializable
    data object PreferencesScreen : Screen()

    @Serializable
    data object LoginScreen : Screen()

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "UploadScreen" -> toRoute<UploadScreen>()
            "InfoScreen" -> toRoute<InfoScreen>()
            "PreferencesScreen" -> toRoute<PreferencesScreen>()
            "LoginScreen" -> toRoute<LoginScreen>()
            else -> null
        }

}