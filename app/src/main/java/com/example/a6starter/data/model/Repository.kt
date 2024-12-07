package com.example.a6starter.data.model

import com.example.a6starter.data.entities.GPreferences
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.entities.UpdatePreferencesResponse
import com.example.a6starter.data.entities.loginUser
import com.example.a6starter.data.entities.matchesFound
import com.example.a6starter.data.entities.newUser
import com.example.a6starter.data.remote.Api
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: Api,
) {
    suspend fun getPreferences(): Response<GPreferences> {
        return api.getPreferences();
    }

    suspend fun uploadSchedule(file: MultipartBody.Part): Boolean {
        return try {
            val response = api.uploadSchedule(file)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updatePreferences(preferences: SPreferences): Response<UpdatePreferencesResponse> {
        return api.updatePreferences(preferences)
    }

    suspend fun getSearchResults(): Response<matchesFound> {
        return api.searchResults()
    }

    suspend fun login(netid: String, password: String) {
        api.loginUser(loginUser(netid, password))
    }

    suspend fun signup(name: String, netId: String, password: String, confirmPassword: String) {
        api.createUser(newUser(name, netId, password, confirmPassword))
    }

    suspend fun logout() {
        api.logoutUser()
    }
}
//TODO ADD MORE API INTERACTIONS AS NEEDED