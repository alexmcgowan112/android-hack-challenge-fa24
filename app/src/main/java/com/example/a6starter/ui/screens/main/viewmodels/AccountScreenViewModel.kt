package com.example.a6starter.ui.screens.main.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val netidFlow = MutableStateFlow("placeholder")
    val netidViewState = netidFlow.asStateFlow()

    fun logout(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("LOGGED_IN", false)
        editor.apply()
    }

    // TODO - Get netid from backend
}