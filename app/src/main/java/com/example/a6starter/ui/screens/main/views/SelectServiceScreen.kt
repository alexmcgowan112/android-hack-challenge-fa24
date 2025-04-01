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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    navigateToMainScreens: () -> Unit,
    loginScreenViewModel: SelectServiceViewModel = hiltViewModel()
){
    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(brush)
        .padding(20.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                InfoButton(onClick = { /* Handle click */ })
            }
            LoginCard(label = "Login", onClick = navigateToMainScreens)
            ServiceCard(label = "Study Buddy", description = "Find a study buddy",
                imageResource = R.drawable.studybuddy, onClick = { /* Handle click */ }
            )

            ServiceCard(label = "Study Buddy", description = "Find a study buddy",
                imageResource = R.drawable.studybuddy, onClick = { /* Handle click */ }
            )

            ServiceCard(label = "Vacant", description = "Find a study buddy",
                imageResource = R.drawable.studybuddy, onClick = { /* Handle click */ }
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

// Notes: Not sure if a description is needed maybe I will add an info button
@Composable
fun ServiceCard(label: String, description: String, imageResource: Int, onClick: () -> Unit) {
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
            contentDescription = "Information Icon"
        )
    }
}
