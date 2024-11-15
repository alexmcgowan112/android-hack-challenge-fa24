package com.example.a6starter.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.entities.DogBreed
import com.example.a6starter.data.model.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainScreenViewState(
    val property1: Unit = TODO("Specify your Main Screen View State")
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    dogRepository: DogRepository,
) : ViewModel() {
    private val favoritesFlow = MutableStateFlow(emptyList<String>())
    private val allBreedsFlow = MutableStateFlow(emptyList<DogBreed>())
    private var currentPage = 0

    /**
     * The current view state of the main screen.
     * This should hold all of the data the UI needs to display.
     */
    val mainScreenViewState = combine(favoritesFlow, allBreedsFlow) { favorites, allBreeds ->
        // TODO
    }.stateIn(viewModelScope, SharingStarted.Eagerly, MainScreenViewState(Unit))

    init {
        loadNextPage()
    }


    fun addFavoriteBreed(dogBreedId: String) {
        // TODO
    }

    fun removeFavoriteBreed(dogBreedId: String) {
        // TODO
    }

    // Don't make this function private, you will need to call it in MainScreen
    //  when you get to the end of the page
    fun loadNextPage() = viewModelScope.launch {
        // TODO use dogRepository and currentPage to load the next page
    }
}