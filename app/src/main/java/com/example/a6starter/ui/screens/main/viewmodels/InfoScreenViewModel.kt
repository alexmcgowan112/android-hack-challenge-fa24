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

data class InfoScreenViewState(
    val matches: List<matchInfo> = mutableListOf()
)

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val matchesFlow = MutableStateFlow(emptyList<matchInfo>())
    private val _emailSendState = MutableStateFlow<EmailSendState>(EmailSendState.Idle)
    val emailSendState = _emailSendState.asStateFlow()
    val matchesViewState = matchesFlow.asStateFlow()

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

    // TODO - Methods
    // Methods we might need.
    private fun refreshData(){
        viewModelScope.launch {
            // We might need these extra repository interactions but maybe not.
        //  val updatedPreferences = repository.getUserPreferences()
            val updatedResults = repository.getSearchResults()
            matchesFlow.update { updatedResults.body()?.matches ?: emptyList<matchInfo>()}
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
        }
        fun resetEmailSendState() {
            _emailSendState.value = EmailSendState.Idle
        }

    }
    /*
    private fun updatePreferences(preferences: UserPreferences) {
        viewModelScope.launch {
            repository.updatePreferences(preferences)
        }
    }
     */
}