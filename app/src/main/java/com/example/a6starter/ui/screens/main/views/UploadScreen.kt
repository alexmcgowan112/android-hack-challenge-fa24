package com.example.a6starter.ui.screens.main.views

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a6starter.ui.theme.A6StarterTheme

// TODO - make this communicate with the backend (in the viewmodel)

@Composable
fun UploadScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp), contentAlignment = Alignment.Center) {
        FileSelector {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun UploadPreview() {
    A6StarterTheme {
        UploadScreen()
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
                Toast.makeText(context,
                    "Please select a valid .ics file.", Toast.LENGTH_SHORT).apply {
                        setGravity(android.view.Gravity.CENTER, 0, 0)
                        show()
                }
                selectedFile.value = null
            }else{
                Toast.makeText(context,
                    "Please Wait!", Toast.LENGTH_SHORT).apply {
                    setGravity(android.view.Gravity.CENTER, 0, 0)
                    show()
                }
                selectedFile.value = uri
            }
        }
    }
    // TODO: DO STUFF WITH FILE NOW
    // You cannot get the ics file directly on the mobile app for GCAL
    // They have to go to their computers and download it then send it to their mobile device.
    // Correct me if I'm wrong. (Maybe it is possible on the search engine in mobile
    // or other calendar services)

    Button(onClick = {
        launcher.launch(arrayOf("text/calendar", "application/octet-stream", "*/*"))
    // MIME types like "application/pdf", "image/*", etc.
    },
        modifier = Modifier.padding(16.dp).shadow(8.dp, shape = RoundedCornerShape(12.dp))) {
        Text(text = "Upload Schedule")
    }

    selectedFile.value?.let {
        Text(text = "Selected File: $it")
    }

}