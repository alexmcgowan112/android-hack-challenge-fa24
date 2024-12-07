package com.example.a6starter.data.remote

import com.example.a6starter.data.entities.GPreferences
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.entities.UpdatePreferencesResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Api {
    //These are just placeholders for now.

    // Get student preferences (GET request for student preferences)
    @GET("preferences")
    suspend fun getStudentPreferences(): Response<GPreferences>

    // Upload schedule (POST request for .ics file)
    @POST("user/upload")
    @Multipart
    suspend fun uploadSchedule(
        @Part file: MultipartBody.Part
    ): Response<Unit>

    // Update user preferences
    @POST("user/preferences")
    suspend fun updatePreferences(
        @Body preferences: SPreferences
    ): Response<UpdatePreferencesResponse>

    // TODO specify your API
    // test
    suspend fun login(
        // TODO
    ): Response<Unit>
}