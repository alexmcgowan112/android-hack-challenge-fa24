package com.example.a6starter.ui.screens.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a6starter.ui.theme.A6StarterTheme

// TODO - make this communicate with the backend (in the viewmodel)

@Composable
fun UploadScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
        Button(onClick = {
            // TODO - Actually select a file
        }) {
            Text("Upload Schedule")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UploadPreview() {
    A6StarterTheme {
        UploadScreen()
    }
}