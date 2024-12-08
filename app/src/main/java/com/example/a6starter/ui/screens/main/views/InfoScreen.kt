package com.example.a6starter.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a6starter.data.entities.matchInfo
import com.example.a6starter.ui.screens.main.viewmodels.InfoScreenViewModel
import com.example.a6starter.ui.theme.Theme
import java.util.Locale

@Composable
fun InfoScreen(infoScreenViewModel: InfoScreenViewModel = hiltViewModel()) {
    val currentViewState = infoScreenViewModel.matchesViewState.collectAsState().value
    val emailSendState by infoScreenViewModel.emailSendState.collectAsState()
    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )
    val lazyListState = rememberLazyListState()
    val lazyListState2 = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Study Buddy Matches",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            if (currentViewState.isEmpty()) {
                Text(
                    text = "No matches found",
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(currentViewState) { match ->
                        MatchCard(
                            match = match,
                            onContactBuddy = {
                                infoScreenViewModel.sendEmail(match.netid)
                            }
                        )
                    }
                }
            }
            LazyColumn(state = lazyListState2,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(currentViewState) {
                    Text("NetID: ${it.netid}")
                }
            }
            Button(onClick = { infoScreenViewModel.refreshData() }) {
                Text("Refresh")
            }
        }
    }
}

@Composable
fun MatchCard(match: matchInfo, onContactBuddy: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6D44BF)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp) // Rounded corners
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF6D44BF),
                            Color(0xFF9E6DDE)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            // Name and NetID
            Text(
                text = "${match.name} (${match.netid})",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            // Match Score
            Text(
                text = "Match Score: ${String.format(Locale.US, "%.2f", match.match_score * 100)}%",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Common Courses
            Text(
                text = "Common Courses:",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 12.dp)
            )
            match.common_courses.forEach { course ->
                Text(
                    text = "• $course",
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            // Common Preferences
            Text(
                text = "Common Preferences:",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = "Locations: ${match.common_preferences.locations.joinToString(", ")}",
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
            Text(
                text = "Times: ${match.common_preferences.times.joinToString(", ")}",
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )
            Text(
                text = "Objectives: ${match.common_preferences.objectives.joinToString(", ")}",
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
            )

            // Contact Buddy Button
            ContactBuddy(
                netid = match.netid,
                onContactBuddy = onContactBuddy
            )
        }
    }
}


@Composable
fun ContactBuddy(
    netid: String,
                 onContactBuddy: (String) -> Unit) {
    Button(onClick = { onContactBuddy(netid) }) {
        Text("Contact Buddy!")
    }
}
@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    Theme {
        InfoScreen()
    }
}