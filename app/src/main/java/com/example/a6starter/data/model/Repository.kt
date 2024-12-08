package com.example.a6starter.data.model

import android.util.Log
import com.example.a6starter.data.entities.GPreferences
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.entities.UpdatePreferencesResponse
import com.example.a6starter.data.entities.loginUser
import com.example.a6starter.data.entities.matchesFound
import com.example.a6starter.data.entities.messageResponse
import com.example.a6starter.data.entities.newUser
import com.example.a6starter.data.entities.userResponse
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

            if(!response.isSuccessful) {
                Log.e("Error response", response.errorBody()?.string() ?: "null")
            } else {
                Log.d("Response", response.body().toString())
            }

            response.isSuccessful
        } catch (e: Exception) {
            e.message?.let { Log.e("Upload error", it) }
            false
        }
    }
    suspend fun sendEmail(netid: String): Response<messageResponse>{
        return api.sendEmail(netid)
    }
    suspend fun updatePreferences(preferences: SPreferences): Response<UpdatePreferencesResponse> {
        return api.updatePreferences(preferences)
    }

    suspend fun getSearchResults(): Response<matchesFound> {
        return api.searchResults()
    }

    suspend fun login(netid: String, password: String): Response<userResponse>  {
        return api.loginUser(loginUser(netid, password))
    }

    suspend fun signup(name: String, netId: String, password: String, confirmPassword: String): Response<userResponse> {
        return api.createUser(newUser(name, netId, password, confirmPassword))
    }

    suspend fun logout(): Response<messageResponse> {
        return api.logoutUser()
    }
}