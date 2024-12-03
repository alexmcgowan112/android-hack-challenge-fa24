package com.example.a6starter.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a6starter.ui.theme.A6StarterTheme

// TODO - make this communicate with the backend (in the viewmodel)

@Composable
fun LoginScreen() {
    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )
    var hasAccount by remember { mutableStateOf(true) }
    var netid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    Box (modifier = Modifier.fillMaxSize()

        .background(brush)
        .padding(20.dp),
        contentAlignment = Alignment.Center) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)) {
            if(hasAccount) {
                Text("Login", fontSize = 30.sp)
                Column {
                    Text("NetID: ")
                    TextField(netid,
                        onValueChange = { netid = it },
                        placeholder = { Text("Type NetID Here") })
                }
                Column {
                    Text("Password: ")
                    TextField(password,
                        onValueChange = { password = it },
                        placeholder = { Text("Type Password Here") })
                }
                Button(onClick = {/*TODO*/}) {
                    Text("Login")
                }
                Button(onClick = {hasAccount = false}) {
                    Text("Don't Have An Account?")
                }
            } else {
                var name by remember { mutableStateOf("") }
                var confirmPassword by remember { mutableStateOf("")}
                Text("Sign Up", fontSize = 30.sp)
                Column {
                    Text("Name: ")
                    TextField(name,
                        onValueChange = { name = it },
                        placeholder = { Text("Type Name Here") })
                }
                Column {
                    Text("NetID: ")
                    TextField(netid,
                        onValueChange = { netid = it },
                        placeholder = { Text("Type NetID Here") })
                }
                Column {
                    Text("Password: ")
                    TextField(password,
                        onValueChange = { password = it },
                        placeholder = { Text("Type Password Here") })
                }
                Column {
                    Text("Confirm Password: ")
                    TextField(confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = { Text("Confirm Password Here") })
                }
                Button(onClick = {
                    hasAccount = true
                    /*TODO*/
                }) {
                    Text("Sign Up")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    A6StarterTheme {
        LoginScreen()
    }
}