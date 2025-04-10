package com.example.a6starter.ui.screens.main.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a6starter.ui.screens.main.viewmodels.SelectServiceViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.a6starter.R

@Composable
fun SelectServiceScreen(
    onLoginClick: () -> Unit,
    onStudyBuddyClick: () -> Unit,
    selectScreenViewModel: SelectServiceViewModel = hiltViewModel()
){
    val currentViewState = selectScreenViewModel.selectServiceViewState.collectAsState().value
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            InfoBox(currentViewState.infoBox, selectScreenViewModel::toggleInfoBox)

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                InfoButton(onClick = {
                    selectScreenViewModel.toggleInfoBox()
                })
            }
            LoginCard(
                label = "Login",
                onClick = { onLoginClick() }
            )

            ServiceCard(
                label = "Study Buddy",
                imageResource = R.drawable.studybuddy,
                onClick = {
                    selectScreenViewModel.handleServiceSelection(
                        selectedService = "Study Buddy",
                        navigateToScreen = { /* Navigate to Study Buddy screen */ },
                        showLoginPrompt = { onStudyBuddyClick() },
                        isLoggedIn = false // Placeholder: Replace with actual logged-in state
                    )
                }
            )

            ServiceCard(
                label = "Plate Pal",
                imageResource = R.drawable.fooding,
                onClick = {
                    selectScreenViewModel.handleServiceSelection(
                        selectedService = "Plate Pal",
                        navigateToScreen = { /* Navigate to Plate Pal screen */ },
                        showLoginPrompt = { /* No login prompt needed */ },
                        isLoggedIn = true // Plate Pal doesn't depend on login; set to true by default
                    )
                }
            )

            ServiceCard(
                label = "Under Construction",
                imageResource = R.drawable.construction,
                onClick = {
                    println("Under Construction")
                }
            )
        }

    }

}

@Composable
fun LoginCard(label: String, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = label)
        }
    }
}

@Composable
fun ServiceCard(label: String, imageResource: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
            .height(300.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = label,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center

            ) {
                Text(text = label)
            }
        }
    }
}

@Composable
fun InfoButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Information"
        )
    }
}

@Composable
fun InfoBox(isVisible: Boolean, onClose: () -> Unit){
    if (!isVisible) return
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .background(Color.Gray.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Information")
            IconButton(
                onClick = onClose,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Red
                )
            }
        }

    }
}
