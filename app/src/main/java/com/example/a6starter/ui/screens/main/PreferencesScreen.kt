package com.example.a6starter.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreferencesScreen() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Set Your Preferences", fontSize = 30.sp)
        Text("Study Session Objective:")
        CheckBox("Review")
        CheckBox("Homework")
    }

}

@Composable
fun CheckBox(boxString: String) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            boxString
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }

    /**Text(
    if (isChecked) "Checkbox is checked" else "Checkbox is unchecked"
    )**/
}