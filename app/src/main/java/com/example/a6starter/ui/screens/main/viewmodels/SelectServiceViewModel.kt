package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

// Define the view state data class for the Select Service screen.
data class SelectServiceViewState(
    val availableServices: List<String> = emptyList(),
    val selectedService: String? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false
)

@HiltViewModel
class SelectServiceViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // Flow holding the list of available services.
    private val availableServicesFlow = MutableStateFlow<List<String>>(emptyList())
    // Flow holding the currently selected service (if any).
    private val selectedServiceFlow = MutableStateFlow<String?>(null)
    // Flow to hold any error messages.
    private val errorMessageFlow = MutableStateFlow<String?>(null)
    // Flow for the loading state.
    private val loadingFlow = MutableStateFlow(false)

    /**
     * The current view state for the service selection screen.
     * It combines available services, the selected service, error messages, and loading state.
     */
    val selectServiceViewState: StateFlow<SelectServiceViewState> =
        combine(
            availableServicesFlow,
            selectedServiceFlow,
            errorMessageFlow,
            loadingFlow
        ) { services, selected, error, loading ->
            SelectServiceViewState(
                availableServices = services,
                selectedService = selected,
                errorMessage = error,
                loading = loading
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = SelectServiceViewState()
        )

    // Initialize by fetching available services.
    init {
        fetchServices()
    }

    /**
     * Fetches the list of available services from the repository.
     */
    // Just in case some sort of api is down might not be needed honestly. This whole thing
    // is a placeholder for now. Might just hardcode this stuff as it isn't really a needed aspect.
    fun fetchServices() {
        viewModelScope.launch {
            loadingFlow.value = true
            try {
                val response = repository.getAvailableServices()
                if (response.isSuccessful) {
                    availableServicesFlow.value = response.body() ?: emptyList()
                } else {
                    errorMessageFlow.value = "Error fetching services: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessageFlow.value = e.message
            } finally {
                loadingFlow.value = false
            }
        }
    }

    /**
     * Allows the UI to set the currently selected service.
     */
    fun selectService(service: String) {
        selectedServiceFlow.value = service
    }
}
