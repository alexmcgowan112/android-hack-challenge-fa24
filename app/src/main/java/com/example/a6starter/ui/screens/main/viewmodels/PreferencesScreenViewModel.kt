package com.example.a6starter.ui.screens.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.entities.GPreferences
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PreferencesScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow = _errorFlow.asStateFlow()

    init {
        fetchPreferences()
    }

    private val preferencesFlow = MutableStateFlow(
        SPreferences(
            location_north = null,
            location_south = null,
            location_central = null,
            location_west = null,
            time_morning = null,
            time_afternoon = null,
            time_evening = null,
            objective_study = null,
            objective_homework = null
        )
    )
    val preferences = preferencesFlow.asStateFlow()

    fun fetchPreferences() {
        viewModelScope.launch {
            try {
                val response = repository.getPreferences()
                when {
                    response.isSuccessful -> {
                        val fetchedPreferences = response.body() ?: GPreferences(
                            north = false,
                            south = false,
                            central = false,
                            west = false,
                            morning = false,
                            afternoon = false,
                            evening = false,
                            study = false,
                            homework = false
                        )
                        preferencesFlow.value = SPreferences(fetchedPreferences)
                        Log.d("Load Preferences", (response.errorBody()?.string() ?: "Success").toString())
                    }

                    response.code() == 401 -> {

                        val errorBody = response.errorBody()?.string()

                        _errorFlow.value = "Not logged in"
                    }

                    response.code() == 404 -> {

                        _errorFlow.value = "User not found"
                    }

                    else -> {

                        _errorFlow.value = "Failed to fetch preferences: ${response.code()}"
                    }
                }
            } catch (e: Exception) {

                _errorFlow.value = "Error: ${e.message ?: "Unknown error occurred"}"
                Log.e("Preferences Error", e.message ?: "Unknown")
            }
        }
    }

    fun clearError() {
        _errorFlow.value = null
    }

    fun updatePreference(field: String, value: Boolean) {
        val updatedPreferences = preferencesFlow.value.copy(
            location_north = if (field == "location_north") value else preferencesFlow.value.location_north,
            location_south = if (field == "location_south") value else preferencesFlow.value.location_south,
            location_central = if (field == "location_central") value else preferencesFlow.value.location_central,
            location_west = if (field == "location_west") value else preferencesFlow.value.location_west,
            time_morning = if (field == "time_morning") value else preferencesFlow.value.time_morning,
            time_afternoon = if (field == "time_afternoon") value else preferencesFlow.value.time_afternoon,
            time_evening = if (field == "time_evening") value else preferencesFlow.value.time_evening,
            objective_study = if (field == "objective_study") value else preferencesFlow.value.objective_study,
            objective_homework = if (field == "objective_homework") value else preferencesFlow.value.objective_homework
            // only updates the preference of field that is passed in
        )
        preferencesFlow.value = updatedPreferences
        viewModelScope.launch {
            try {
                val response = repository.updatePreferences(preferencesFlow.value)
                when {
                    response.isSuccessful -> {
                        val updateResult = response.body()
                        println("Preferences updated: ${updateResult?.message}")
                    }

                    response.code() == 401 -> {
                        _errorFlow.value = "Not logged in"
                    }

                    response.code() == 404 -> {
                        _errorFlow.value = "User not found"
                    }

                    response.code() == 400 -> {
                        val errorBody = response.errorBody()?.string()
                        _errorFlow.value = "Invalid preference: $errorBody"
                    }

                    else -> {
                        _errorFlow.value = "Failed to update preferences: ${response.code()}"
                    }
                }
            } catch (e: Exception) {
                _errorFlow.value = "Error updating preferences: ${e.message}"
            }
        }

    }
}