package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                if (response.isSuccessful) {
                    println("Preferences updated successfully")
                } else {

                    println("Error updating preferences: ${response.code()}")
                }
            } catch (e: Exception) {

                println("Error updating preferences: ${e.message}")
            }
        }

    }

    // TODO - Functions
    /**
    fun updatePreference(
    preferenceName: String, newState: Boolean
    ) {
    preferencesFlow.value = preferencesFlow.value.toMutableMap().apply {
    put(preferenceName, newState)
    }
    // TODO - Update backend
    }**/
}