package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class PreferencesScreenViewModel @Inject constructor(
//    private val repository: Repository,
) : ViewModel() {
    private val preferencesFlow = MutableStateFlow(emptyMap<String, Boolean>())
    val preferencesViewState = preferencesFlow.asStateFlow().value

    // TODO - Functions
    fun updatePreference(
        preferenceName: String, newState: Boolean
    ) {
        preferencesFlow.value = preferencesFlow.value.toMutableMap().apply {
            put(preferenceName, newState)
        }
    }
}