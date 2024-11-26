package com.example.a6starter.ui.screens.main

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
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InfoScreenViewState(
    val property1: Unit = TODO("Specify your Main Screen View State")
)

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    // FIXME - Placeholders
    private val favoritesFlow = MutableStateFlow(emptyList<String>())
    private val allBreedsFlow = MutableStateFlow(emptyList<DogBreed>())
    private var currentPage = 0

    /**
     * The current view state of the main screen.
     * This should hold all of the data the UI needs to display.
     * This value is derived from `combine` with the favorite dog breeds and all dog breeds.
     * Each time either of the flows update, we call `createViewState` to get a new view state that
     * reflects the updated information.
     */
    // FIXME - Placeholders
    val mainScreenViewState: StateFlow<MainScreenViewState> =
        combine(favoritesFlow, allBreedsFlow) { favorites, allBreeds ->
            createViewState(favorites, allBreeds)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, MainScreenViewState())

    init {
        loadNextPage()
    }

    // FIXME - Placeholders
    private fun createViewState(
        favorites: List<String>,
        allBreeds: List<DogBreed>
    ): MainScreenViewState {
            TODO("Fill out this function to create your view state")
    }


    // Don't make this function private, you will need to call it in MainScreen
    //  when you get to the end of the page
    // FIXME - Placeholders
    fun loadNextPage() = viewModelScope.launch {
        // TODO use dogRepository and currentPage to load the next page, updating allBreedsFlow
    }
}