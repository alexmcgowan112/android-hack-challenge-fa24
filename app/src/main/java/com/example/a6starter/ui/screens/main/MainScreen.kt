package com.example.a6starter.ui.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    Text("TODO: Create your main screen here, note that the view model is given to you.")
    Text("viewModel = $viewModel")
}