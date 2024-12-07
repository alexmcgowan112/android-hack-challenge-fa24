package com.example.a6starter.data.model

import com.example.a6starter.data.entities.GPreferences
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.entities.UpdatePreferencesResponse
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
}
//TODO ADD MORE API INTERACTIONS AS NEEDED