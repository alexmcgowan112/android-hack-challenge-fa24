package com.example.a6starter.ui.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val LOADING_KEY = "LOADING"

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    Text(
        "TODO: Create your main screen here, note that you can access the viewModel from " +
                "the composable parameter ($viewModel)"
    )
    val lazyListState = rememberLazyListState()
    // To see when we need to load more data, we create a stateful variable based on the lazy list
    //  state. It is true if our loading circle is visible.
    val loadingCircleVisible by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.any { it.key == LOADING_KEY }
        }
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // We then use snapshotFlow to convert this stateful variable into a flow, so this way
        //  we can observe and react to its changes.
        snapshotFlow { loadingCircleVisible }.onEach {
            // TODO call load next page here
        }.launchIn(coroutineScope)
    }

    LazyColumn(state = lazyListState) {
        items(TODO("Add your list items here")) {

        }
        item(key = LOADING_KEY) {
            CircularProgressIndicator()
        }
    }
}