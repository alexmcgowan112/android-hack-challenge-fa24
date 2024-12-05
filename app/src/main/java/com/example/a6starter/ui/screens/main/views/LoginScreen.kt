package com.example.a6starter.ui.screens.main.views

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a6starter.ui.screens.main.viewmodels.LoginScreenViewModel
import com.example.a6starter.ui.theme.Theme

@Composable
fun LoginScreen(
    loginScreenViewModel: LoginScreenViewModel = viewModel(),
    sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences(
        "LOGGED_IN",
        Context.MODE_PRIVATE
    )
) {
    val currentViewState = loginScreenViewModel.loginScreenViewState.collectAsState().value

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
            if(currentViewState.hasAccount) {
                Login(loginScreenViewModel, sharedPreferences)
            } else {
                Signup(loginScreenViewModel, sharedPreferences)
            }
        }
    }
}

@Composable
fun TextFieldWithLabel(label: String, text: String, onValueChange: (String) -> Unit, placeholder: String) {
    Column {
        Text(label)
        TextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) }
        )
    }
}

@Composable
fun ErrorMessage(messageText: String?) {
    if(messageText!= null) {
        Text(messageText, color = Color.Red)
    }
}

@Composable
fun Login(loginScreenViewModel: LoginScreenViewModel, sharedPreferences: SharedPreferences) {
    var netid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Text("Login", fontSize = 30.sp)

    TextFieldWithLabel("NetID: ", netid, { netid = it }, "Type NetID Here")

    TextFieldWithLabel("Password: ", password, { password = it }, "Type Password Here")

    ErrorMessage(loginScreenViewModel.loginScreenViewState.collectAsState().value.errorMessage)

    Button(onClick = { loginScreenViewModel.login(sharedPreferences, netid, password) }) {
        Text("Login")
    }
    Button(onClick = {loginScreenViewModel.noAccount()}) {
        Text("Don't Have An Account?")
    }
}

@Composable
fun Signup(loginScreenViewModel: LoginScreenViewModel, sharedPreferences: SharedPreferences) {
    var name by remember { mutableStateOf("") }
    var netid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("")}

    Text("Sign Up", fontSize = 30.sp)

    TextFieldWithLabel("Name:", name, { name = it }, "Type Name Here")

    TextFieldWithLabel("NetID:", netid, { netid = it }, "Type NetID Here")

    TextFieldWithLabel("Password:", password, { password = it }, "Type Password Here")

    TextFieldWithLabel("Confirm Password:", confirmPassword, { confirmPassword = it }, "Confirm Password Here")

    ErrorMessage(loginScreenViewModel.loginScreenViewState.collectAsState().value.errorMessage)

    Button(onClick = {loginScreenViewModel.signup(sharedPreferences, name, netid, password, confirmPassword)}) {
        Text("Sign Up")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Theme {
        LoginScreen()
    }
}