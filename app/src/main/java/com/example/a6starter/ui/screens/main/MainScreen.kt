package com.example.a6starter.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val LOADING_KEY = "LOADING"

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {

    val lazyListState = rememberLazyListState()
    // To see when we need to load more data, we create a stateful variable based on the lazy list
    //  state. It is true if our loading circle is visible.
    /**val loadingCircleVisible by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.any { it.key == LOADING_KEY }
        }
    }**/
    val coroutineScope = rememberCoroutineScope()

    /**LaunchedEffect(Unit) {
        // We then use snapshotFlow to convert this stateful variable into a flow, so this way
        //  we can observe and react to its changes.
        snapshotFlow { loadingCircleVisible }.onEach {
            // TODO call load next page here
        }.launchIn(coroutineScope)
    }**/
    val tempList = listOf(1,2,3,4)
    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
    Column(modifier = Modifier.padding(10.dp).background(Color.LightGray)) {
        Text(text = displayText)
    }
}
