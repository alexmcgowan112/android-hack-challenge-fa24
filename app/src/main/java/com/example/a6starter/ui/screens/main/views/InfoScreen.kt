package com.example.a6starter.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a6starter.ui.theme.A6StarterTheme

// TODO - make this display actually meaningful data (from the viewmodel)

@Composable
fun InfoScreen() {
    val lazyListState = rememberLazyListState()
    Box (modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Text(text = "Main Screen", fontSize = 40.sp, textAlign = TextAlign.Center)
            LazyColumn(state = lazyListState) {
                items(100) { index ->
                    ColumnCard(index.toString())
                }
            }
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

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    A6StarterTheme {
        InfoScreen()
    }
}