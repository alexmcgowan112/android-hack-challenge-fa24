package com.example.a6starter.ui.screens.main.viewmodels
import androidx.lifecycle.ViewModel
import com.example.a6starter.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectServiceViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    /*
    The idea here is that we want to extend this app as a multifunctional app. So well will have two
    main functionalities for now:
        - Finding Study Partners
        - Finding A Place to Eat (How we are different from Eatery: we include the allergies
         of the user v food.))
        - 
     */

}