package com.example.a6starter.ui.screens.main.views

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a6starter.ui.screens.main.viewmodels.LoginScreenViewModel

@Composable
fun LoginScreen(
    navigateToMainScreens: () -> Unit,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
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
                Login(navigateToMainScreens)
            } else {
                Signup(navigateToMainScreens)
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
fun Login(navigateToMainScreens: () -> Unit, loginScreenViewModel: LoginScreenViewModel = hiltViewModel()) {
    var netid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text("Login", fontSize = 30.sp)

    TextFieldWithLabel("NetID: ", netid, { netid = sanitizeString(it) }, "Type NetID Here")

    TextFieldWithLabel("Password: ", password, { password = sanitizeString(it) }, "Type Password Here")

    ErrorMessage(loginScreenViewModel.loginScreenViewState.collectAsState().value.errorMessage)

    Button(onClick = { loginScreenViewModel.login(navigateToMainScreens, netid, password) }) {
        Text("Login")
    }
    Button(onClick = {loginScreenViewModel.noAccount()}) {
        Text("Don't Have An Account?")
    }
}

@Composable
fun Signup(navigateToMainScreens: () -> Unit, loginScreenViewModel: LoginScreenViewModel = hiltViewModel()) {
    var name by remember { mutableStateOf("") }
    var netid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("")}

    Text("Sign Up", fontSize = 30.sp)

    TextFieldWithLabel("Name:", name, { name = it }, "Type Name Here")

    TextFieldWithLabel("NetID:", netid, { netid = sanitizeString(it) }, "Type NetID Here")

    TextFieldWithLabel("Password:", password, { password = sanitizeString(it) }, "Type Password Here")

    TextFieldWithLabel("Confirm Password:", confirmPassword, { confirmPassword = sanitizeString(it) }, "Confirm Password Here")

    ErrorMessage(loginScreenViewModel.loginScreenViewState.collectAsState().value.errorMessage)

    Button(onClick = {loginScreenViewModel.signup(navigateToMainScreens, name, netid, password, confirmPassword)}) {
        Text("Sign Up")
    }

    Button(onClick = {loginScreenViewModel.hasAccount()}) {
        Text("Already Have An Account?")
    }
}

fun sanitizeString(input: String): String {
    return input.replace(" ", "").replace("\n", "")
}