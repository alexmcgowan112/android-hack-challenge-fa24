package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    fun logout(navigateToLoginScreen: () -> Unit) {
        viewModelScope.launch {
            repository.logout()
        }
        navigateToLoginScreen()
    }
}