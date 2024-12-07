package com.example.a6starter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.a6starter.ui.screens.main.views.AccountScreen
import com.example.a6starter.ui.screens.main.views.InfoScreen
import com.example.a6starter.ui.screens.main.views.LoginScreen
import com.example.a6starter.ui.screens.main.views.PreferencesScreen
import com.example.a6starter.ui.screens.main.views.UploadScreen
import com.example.a6starter.ui.theme.Theme
import dagger.hilt.android.AndroidEntryPoint
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
    data object AccountScreen : Screen()

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "UploadScreen" -> toRoute<UploadScreen>()
            "InfoScreen" -> toRoute<InfoScreen>()
            "PreferencesScreen" -> toRoute<PreferencesScreen>()
            "AccountScreen" -> toRoute<AccountScreen>()
            else -> null
        }

}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                val sharedPreferences =
                    getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)

                var loggedIn by remember {
                    mutableStateOf(sharedPreferences.getBoolean("LOGGED_IN", false))
                }

                DisposableEffect(sharedPreferences) {
                    val listener =  { prefs: SharedPreferences, key: String? ->
                        if (key == "LOGGED_IN") {
                            loggedIn = prefs.getBoolean("LOGGED_IN", false)
                        }
                    }
                    sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
                    onDispose {
                        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
                    }
                }

                if(loggedIn) {
                    NavigableScreens()
                } else {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun NavigableScreens() {
    val navController = rememberNavController()
    val tabs = listOf(
        NavItem(
            label = "Info",
            icon = painterResource(id = R.drawable.baseline_supervisor_account_24),
            screen = Screen.InfoScreen
        ),
        NavItem(
            label = "Upload",
            icon = painterResource(id = R.drawable.baseline_file_upload_24),
            screen = Screen.UploadScreen
        ),
        NavItem(
            label = "Preferences",
            icon = painterResource(id = R.drawable.baseline_settings_24),
            screen = Screen.PreferencesScreen
        ),
        NavItem(
            label = "Account",
            icon = painterResource(id = R.drawable.baseline_person_24),
            screen = Screen.AccountScreen
        )

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar(containerColor = Color(172, 201, 252)) {
            tabs.map { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen::class.simpleName,
                    onClick = {
                        navController.navigate(item.screen::class.simpleName!!) {
                            popUpTo(navController.graph.startDestinationRoute!!) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    },
                    icon = { Icon(painter = item.icon, contentDescription = null, tint = Color.Black) },
                    label = { Text(text = item.label, color = Color.Black) },

                    )
            }
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.UploadScreen::class.simpleName!!,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.InfoScreen::class.simpleName!!) {
                InfoScreen()
            }
            composable(Screen.UploadScreen::class.simpleName!!) {
                UploadScreen()
            }
            composable(Screen.PreferencesScreen::class.simpleName!!) {
                PreferencesScreen()
            }
            composable(Screen.AccountScreen::class.simpleName!!) {
                AccountScreen()
            }
        }

    }
}