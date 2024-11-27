package com.example.a6starter.data.model

import com.example.a6starter.data.entities.DogEntity
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.remote.Api
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: Api,
) {
    suspend fun getDogBreeds(netId: Int): Response<SPreferences> {
        return api.getStudentPreferences(netId);
    }
}
//TODO ADD MORE API INTERACTIONS AS NEEDED