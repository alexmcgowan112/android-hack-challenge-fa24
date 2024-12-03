package com.example.a6starter.ui.screens.main.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a6starter.ui.theme.A6StarterTheme

@Composable
fun PreferencesScreen() {
    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Text(text = "Set Your Preferences", fontSize = 30.sp, color = Color.White)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Study Session Objective:", color = Color.White)
            Column(modifier = Modifier.align(Alignment.Start)){
                CheckBox("Review")
                CheckBox("Homework")
            }


            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Preferred Time(s):", color = Color.White)
            Column(modifier = Modifier.align(Alignment.Start)){
                CheckBox("Morning")
                CheckBox("Afternoon")
                CheckBox("Evening")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Preferred Location(s):", color = Color.White)
            Column(modifier = Modifier.align(Alignment.Start)){
                CheckBox("Central")
                CheckBox("North")
                CheckBox("West")
                CheckBox("Collegetown")
            }


            Spacer(modifier = Modifier.height(40.dp))

            UpdatePreferences()
        }
    }
}

@Composable
fun CheckBox(boxString: String) {
    // TODO - make this communicate with the backend (in the viewmodel)
    var isChecked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = boxString,
            color = Color.White
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            //modifier = Modifier.weight(weight = 1.0f, fill = true)

            )
    }

    /**Text(
    if (isChecked) "Checkbox is checked" else "Checkbox is unchecked"
    )**/
}

@Composable
fun UpdatePreferences() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context, "Successfully updated preferences", Toast.LENGTH_SHORT).show()
    }) {
        Text("Update Preferences")
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    A6StarterTheme {
        CheckBox("Homework")

    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesPreview() {
    A6StarterTheme {
        PreferencesScreen()
    }
}
