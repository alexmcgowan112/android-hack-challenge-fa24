package com.example.a6starter.ui.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    Text(
        "TODO: Create your main screen here, note that you can access the viewModel from " +
                "the composable parameter ($viewModel)"
    )
}