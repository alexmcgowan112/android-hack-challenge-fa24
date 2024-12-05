package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    // TODO - Methods
    fun uploadSchedule() {
        // TODO
    }
}