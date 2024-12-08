package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.entities.matchInfo
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val matchesFlow = MutableStateFlow(emptyList<matchInfo>())
    private val _emailSendState = MutableStateFlow<EmailSendState>(EmailSendState.Idle)
    val emailSendState = _emailSendState.asStateFlow()
    val matchesViewState = matchesFlow.asStateFlow()

    //Refresh Swipe
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()


    sealed class EmailSendState {
        object Idle : EmailSendState()
        object Loading : EmailSendState()
        data class Success(val message: String) : EmailSendState()
        data class Error(val errorMessage: String) : EmailSendState()
    }

    init {
        refreshData()
    }

    /**
     * The current view state of the main screen.
     * This should hold all of the data the UI needs to display.
     * This value is derived from `combine` with the favorite dog breeds and all dog breeds.
     * Each time either of the flows update, we call `createViewState` to get a new view state that
     * reflects the updated information.
     */

    // Methods we might need.
    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val updatedResults = repository.getSearchResults()
                matchesFlow.update { updatedResults.body()?.matches ?: emptyList() }
            } catch (e: Exception) {
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun sendEmail(netid: String) {
        viewModelScope.launch {

            _emailSendState.value = EmailSendState.Loading

            try {
                val response = repository.sendEmail(netid)

                if (response.isSuccessful) {

                    _emailSendState.value = EmailSendState.Success(
                        response.body()?.message ?: "Email sent successfully"
                    )
                } else {

                    _emailSendState.value = EmailSendState.Error(
                        response.errorBody()?.string() ?: "Failed to send email"
                    )
                }
            } catch (e: Exception) {

                _emailSendState.value = EmailSendState.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            }
            kotlinx.coroutines.delay(2000)
            resetEmailSendState()
        }
    }
    fun resetEmailSendState() {
        _emailSendState.value = EmailSendState.Idle
    }
}