package com.example.a6starter.ui.screens.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.entities.DogBreed
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class LoginScreenViewState(
    val favorites: List<String> = emptyList<String>(),
    val allBreeds: List<DogBreed> = emptyList<DogBreed>()
)

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    // FIXME - Placeholders
    private val favoritesFlow = MutableStateFlow(emptyList<String>())
    private val allBreedsFlow = MutableStateFlow(emptyList<DogBreed>())

    /**
     * The current view state of the main screen.
     * This should hold all of the data the UI needs to display.
     * This value is derived from `combine` with the favorite dog breeds and all dog breeds.
     * Each time either of the flows update, we call `createViewState` to get a new view state that
     * reflects the updated information.
     */
    // FIXME - Placeholders
    val loginScreenViewState: StateFlow<LoginScreenViewState> =
        combine(favoritesFlow, allBreedsFlow) { favorites, allBreeds ->
            createViewState(favorites, allBreeds)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, LoginScreenViewState())

    // FIXME - Placeholders
    private fun createViewState(
        favorites: List<String>,
        allBreeds: List<DogBreed>
    ): LoginScreenViewState {

        return LoginScreenViewState(
            favorites = favorites,
            allBreeds = allBreeds
        )

    }

    // TODO - Methods
}