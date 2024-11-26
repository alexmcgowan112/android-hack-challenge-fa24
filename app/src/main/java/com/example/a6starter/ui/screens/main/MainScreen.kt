package com.example.a6starter.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.a6starter.R
import kotlinx.serialization.Serializable

private const val LOADING_KEY = "LOADING"

@Serializable
sealed class Screen {
    @Serializable
    data object UploadScreen : Screen()

    @Serializable
    data object InfoScreen : Screen()

    @Serializable
    data object PreferencesScreen : Screen()

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "UploadScreen" -> toRoute<UploadScreen>()
            "InfoScreen" -> toRoute<InfoScreen>()
            "PreferencesScreen" -> toRoute<PreferencesScreen>()
            else -> null
        }

}

data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: Painter
)

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {


    // To see when we need to load more data, we create a stateful variable based on the lazy list
    //  state. It is true if our loading circle is visible.
    /**val loadingCircleVisible by remember {
    derivedStateOf {
    lazyListState.layoutInfo.visibleItemsInfo.any { it.key == LOADING_KEY }
    }
    }**/
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val tabs = listOf(
        NavItem(
            label = "Upload",
            icon = painterResource(id = R.drawable.baseline_file_upload_24),
            screen = Screen.UploadScreen
        ),
        NavItem(
            label = "Info",
            icon = painterResource(id = R.drawable.baseline_supervisor_account_24),
            screen = Screen.InfoScreen
        ),
        NavItem(
            label = "Preferences",
            icon = painterResource(id = R.drawable.baseline_settings_24),
            screen = Screen.PreferencesScreen
        )

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
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
                    icon = { Icon(painter = item.icon, contentDescription = null) },
                    label = { Text(text = item.label) }
                )
            }
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.UploadScreen::class.simpleName!!,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.UploadScreen::class.simpleName!!) {
                UploadScreen()
            }
            composable(Screen.InfoScreen::class.simpleName!!) {
                InfoScreen()
            }
            composable(Screen.PreferencesScreen::class.simpleName!!) {
                PreferencesScreen()
            }
        }

    }

    /**LaunchedEffect(Unit) {
    // We then use snapshotFlow to convert this stateful variable into a flow, so this way
    //  we can observe and react to its changes.
    snapshotFlow { loadingCircleVisible }.onEach {
    // TODO call load next page here
    }.launchIn(coroutineScope)
    }**/


}

@Composable
fun UploadScreen() {
    val tempList = listOf(1, 2, 3, 4)
    val lazyListState = rememberLazyListState()
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Main Screen", fontSize = 40.sp, textAlign = TextAlign.Center)
        LazyColumn(state = lazyListState) {
            items(tempList) { index ->
                ColumnCard(index.toString())
            }
            //item(key = LOADING_KEY) {
            //    CircularProgressIndicator()
            //}
        }
    }
}

@Composable
fun ColumnCard(displayText: String) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.LightGray)
    ) {
        Text(text = displayText)
    }
}
