package com.example.a6starter.ui.screens.main.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody


data class UploadScreenViewState(
    val uploadState: UploadState = UploadState.Loading
)

sealed class UploadState {
    object Success : UploadState()
    object Failure : UploadState()
    object Error : UploadState()
    object EmptyUri : UploadState()
    object Loading : UploadState()
}

@HiltViewModel
class UploadScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _uploadState = MutableStateFlow<UploadState>(UploadState.EmptyUri)
    val uploadState: StateFlow<UploadState> = _uploadState
    /**
     * The current view state of the main screen.
     * This should hold all of the data the UI needs to display.
     * This value is derived from `combine` with the favorite dog breeds and all dog breeds.
     * Each time either of the flows update, we call `createViewState` to get a new view state that
     * reflects the updated information.
     */
    val uploadScreenViewState: StateFlow<UploadScreenViewState> =
        _uploadState.map { uploadState ->
            UploadScreenViewState(uploadState)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, UploadScreenViewState())

    fun uploadFile(uri: Uri?, context: Context) {
        viewModelScope.launch {
            try {
                val contentResolver = context.contentResolver
                val inputStream = uri?.let { contentResolver.openInputStream(it) }

                if (inputStream != null) {
                    val fileContent = inputStream.readBytes()
                    inputStream.close()

                    // Create RequestBody
                    val requestBody = fileContent.toRequestBody("text/calendar".toMediaTypeOrNull())

                    // Wrap in MultipartBody.Part with a filename
                    // Parts are name, filename, and body
                    val part = MultipartBody.Part.createFormData(
                        name = "file",
                        filename = "schedule.ics",
                        body = requestBody
                    )

                    // Call the repository
                    val success = repository.uploadSchedule(part)

                    if (success) {
                        _uploadState.value = UploadState.Success
                    } else {
                        _uploadState.value = UploadState.Failure
                    }
                } else {
                    Log.e("UploadViewModel", "Failed to open input stream for URI: $uri")
                    _uploadState.value = UploadState.Error
                }
            } catch (e: Exception) {
                _uploadState.value = UploadState.Error
                Log.e("Upload Error", e.message.toString())
            }
        }
    }
}