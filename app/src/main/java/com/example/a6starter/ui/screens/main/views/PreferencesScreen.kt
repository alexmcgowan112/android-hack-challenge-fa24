package com.example.a6starter.ui.screens.main.views

import android.graphics.fonts.FontStyle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a6starter.ui.screens.main.viewmodels.PreferencesScreenViewModel
import com.example.a6starter.ui.theme.Theme
import java.time.format.TextStyle

@Composable
fun PreferencesScreen(viewModel: PreferencesScreenViewModel = hiltViewModel()) {
    val preferences by viewModel.preferences.collectAsState()
    val error by viewModel.errorFlow.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(error) {
        error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()

            viewModel.clearError()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(96, 150, 253), Color(170, 182, 251)))),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Set Your Preferences", fontSize = 30.sp, color = Color.White, style = androidx.compose.ui.text.TextStyle(shadow = Shadow(color = Color.Black, offset = Offset(5f, 10f), blurRadius = 19f)))

            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Study Session Objective:", color = Color.White)



            CheckBox("Study", preferences.objective_study ?: false) {
                viewModel.updatePreference("objective_study", it)
            }
            CheckBox("Homework", preferences.objective_homework ?: false) {
                viewModel.updatePreference("objective_homework", it)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Preferred Time(s):", color = Color.White)
            CheckBox("Morning", preferences.time_morning ?: false) {
                viewModel.updatePreference("time_morning", it)
            }
            CheckBox("Afternoon", preferences.time_afternoon ?: false) {
                viewModel.updatePreference("time_afternoon", it)
            }
            CheckBox("Evening", preferences.time_evening ?: false) {
                viewModel.updatePreference("time_evening", it)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Preferred Location(s):", color = Color.White)
            CheckBox("Central", preferences.location_central ?: false) {
                viewModel.updatePreference("location_central", it)
            }
            CheckBox("North", preferences.location_north ?: false) {
                viewModel.updatePreference("location_north", it)
            }
            CheckBox("West", preferences.location_west ?: false) {
                viewModel.updatePreference("location_west", it)
            }
            CheckBox("South", preferences.location_south ?: false) {
                viewModel.updatePreference("location_south", it)
            }

            Spacer(modifier = Modifier.height(40.dp))
            UpdatePreferences()
        }
    }
}

@Composable
fun CheckBox(field: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Text(text = field, color = Color.White)
    }
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
fun PreferencesPreview() {
    Theme {
        PreferencesScreen()
    }
}