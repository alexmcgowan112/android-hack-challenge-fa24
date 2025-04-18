package com.example.a6starter.ui.screens.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginScreenViewState(
    val hasAccount: Boolean = true,
    val errorMessage: String? = null
)

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val hasAccountFlow = MutableStateFlow(true)
    private val errorMessageFlow = MutableStateFlow<String?>(null)

    /**
     * The current view state of the main screen.
     * This should hold all of the data the UI needs to display.
     * This value is derived from `combine` with the favorite dog breeds and all dog breeds.
     * Each time either of the flows update, we call `createViewState` to get a new view state that
     * reflects the updated information.
     */
    val loginScreenViewState: StateFlow<LoginScreenViewState> =
        combine(hasAccountFlow, errorMessageFlow) { hasAccount, errorMessage ->
            createViewState(hasAccount, errorMessage)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, LoginScreenViewState())

    private fun createViewState(
        hasAccount: Boolean,
        errorMessage: String?
    ): LoginScreenViewState {

        return LoginScreenViewState(
            hasAccount = hasAccount,
            errorMessage = errorMessage
        )

    }

    fun login(navigateToMainScreens: () -> Unit, netid: String, password: String) {
        if (listOf(netid, password).any { it.isEmpty() }) {
            errorMessageFlow.value = "One or more fields are empty!"
        } else {
            errorMessageFlow.value = null

            viewModelScope.launch {
                try{
                    delay(500)
                    val response = repository.login(netid, password)
                    when{
                        response.isSuccessful -> {

                            navigateToMainScreens()
                        }
                        response.code() == 400 -> {
                            errorMessageFlow.value = "Invalid credentials"
                        }
                        response.code() == 404 -> {
                            errorMessageFlow.value = "User not found"
                        }
                        else -> {
                            errorMessageFlow.value = "Login failed ${response.code()}"
                        }
                    }

                }catch(e: Exception){
                    Log.d("Login Error", e.message ?: "Unknown error")
                }
            }
        }
    }

    fun noAccount() {
        hasAccountFlow.value = false
        errorMessageFlow.value = null
    }

    fun hasAccount() {
        hasAccountFlow.value = true
        errorMessageFlow.value = null
    }

    fun signup(navigateToMainScreens: () -> Unit, name: String, netid: String, password: String, confirmPassword:String) {
        if (listOf(netid, name, password, confirmPassword).any { it.isEmpty() }) {
            errorMessageFlow.value = "One or more fields are empty!"
        } else if (password != confirmPassword) {
            errorMessageFlow.value = "Passwords do not match!"
        } else {

            viewModelScope.launch {
                val response = repository.signup(name, netid, password, confirmPassword)
                if(response.isSuccessful) {
                    Log.d("Signup Success Code", response.code().toString())
                    hasAccount()
                    login(navigateToMainScreens, netid, password)
                } else {
                    Log.d("Signup Error Message", response.errorBody()?.string() ?: "Null")
                    errorMessageFlow.value = response.errorBody()?.string() ?: "Null"
                }
            }
        }
    }
}