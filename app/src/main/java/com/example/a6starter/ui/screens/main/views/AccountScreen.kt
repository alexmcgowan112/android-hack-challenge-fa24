package com.example.a6starter.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a6starter.ui.screens.main.viewmodels.AccountScreenViewModel

@Composable
fun AccountScreen(navigateToLoginScreen: () -> Unit,
    accountScreenViewModel: AccountScreenViewModel = hiltViewModel()
) {
    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )

    Box (modifier = Modifier
        .fillMaxSize()

        .background(brush)
        .padding(20.dp),
        contentAlignment = Alignment.Center) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Hello!", fontSize = 30.sp)
                Button(onClick = { accountScreenViewModel.logout(navigateToLoginScreen) }) {
                    Text("Log Out")
                }
            }
        }
    }
}