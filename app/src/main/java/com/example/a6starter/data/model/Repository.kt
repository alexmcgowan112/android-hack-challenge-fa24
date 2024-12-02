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
    suspend fun getPreferences(netId: Int): Response<SPreferences> {
        return api.getStudentPreferences(netId);
    }
    suspend fun uploadSchedule(netId: Int, file: okhttp3.MultipartBody.Part): Response<Unit> {
        return api.uploadSchedule(netId, file);
    }
    suspend fun updatePreferences(preferences: SPreferences): Response<Unit> {
        return api.updatePreferences(preferences);
    }
}
//TODO ADD MORE API INTERACTIONS AS NEEDED