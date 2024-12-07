package com.example.a6starter.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a6starter.ui.screens.main.viewmodels.InfoScreenViewModel
import com.example.a6starter.ui.screens.main.viewmodels.LoginScreenViewModel
import com.example.a6starter.ui.theme.Theme

// TODO - make this display actually meaningful data (from the viewmodel)

@Composable
fun InfoScreen(infoScreenViewModel: InfoScreenViewModel = hiltViewModel()) {
    val currentViewState = infoScreenViewModel.matchesViewState.collectAsState().value

    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )
    val lazyListState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Matching Screen",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            LazyColumn(state = lazyListState) {
                items(currentViewState) {
                    Text("NetID: ${it.netid}")
                }
            }
        }
    }
}

@Composable
fun ColumnCard(displayText: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(Color(red = 140, green = 21, blue = 209, alpha = 188)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = displayText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    Theme {
        InfoScreen()
    }
}