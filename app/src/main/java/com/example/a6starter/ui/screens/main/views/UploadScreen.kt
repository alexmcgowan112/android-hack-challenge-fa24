package com.example.a6starter.ui.screens.main.views

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a6starter.ui.screens.main.viewmodels.UploadScreenViewModel
import com.example.a6starter.ui.theme.Theme
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UploadScreen(viewModel: UploadScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val brush = Brush.verticalGradient(
        listOf(
            Color(96, 150, 253),
            Color(170, 182, 251)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FileSelector(
                onFileSelected = { uri ->
                    if (uri == null) {
                        Log.e("UploadScreen", "File selection failed or canceled.")
                    } else {
                        Log.d("UploadScreen", "Selected file URI: $uri")
                        viewModel.uploadFile(uri, context)
                    }
                }
            )
            Hyperlink()
        }
    }
}

@Composable
fun FileSelector(onFileSelected: (Uri?) -> Unit) {
    val context = LocalContext.current

    // Storing the file here.
    val selectedFile = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        selectedFile.value = uri
        onFileSelected(uri)
        uri?.let {
            val mimeType = context.contentResolver.getType(uri)
            if (mimeType != "text/calendar") {
                // Little pop-up message for the user letting them know what they selected is not valid.
                Toast.makeText(
                    context,
                    "Please select a valid .ics file.", Toast.LENGTH_SHORT
                ).apply {
                    setGravity(android.view.Gravity.CENTER, 0, 0)
                    show()
                }
                selectedFile.value = null
            } else {
                Toast.makeText(
                    context,
                    "Please Wait!", Toast.LENGTH_SHORT
                ).apply {
                    setGravity(android.view.Gravity.CENTER, 0, 0)
                    show()
                }
                selectedFile.value = uri
            }
        }
    }

    Button(
        onClick = {
            launcher.launch(arrayOf("text/calendar", "application/octet-stream", "*/*"))
        },
        modifier = Modifier
            .padding(16.dp)
            .shadow(8.dp, shape = RoundedCornerShape(12.dp))
    ) {
        Text(text = "Upload Schedule")
    }

    selectedFile.value?.let {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Selected File:")
            Text(text = "$it")
        }
    }

}

@Composable
fun Hyperlink() {
    val annotatedString = buildAnnotatedString {
        append("Need to Download Your Schedule?: ")
        pushStringAnnotation(
            tag = "URL",
            annotation = "https://classes.cornell.edu/scheduler/roster/SP25"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Click Here")
        }
        pop()
    }

    val uriHandler = LocalUriHandler.current

    Text(
        text = annotatedString,
        modifier = Modifier.clickable {
            // Handle clicks
            annotatedString.getStringAnnotations(tag = "URL", start = 0, end = annotatedString.length)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        },
        style = androidx.compose.ui.text.TextStyle.Default
    )
}

