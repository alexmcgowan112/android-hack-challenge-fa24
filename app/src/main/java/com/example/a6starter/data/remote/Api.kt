package com.example.a6starter.data.remote

import com.example.a6starter.data.entities.SPreferences
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface Api {
    //These are just placeholders for now.

    // Get student preferences (GET request for student preferences)
    @GET("preferences")
    suspend fun getStudentPreferences(
        @Query("student[id]") netId: Int
    ): Response<SPreferences>

    // Upload schedule (POST request for .ics file)
    @POST("user/upload")
    @Multipart
    suspend fun uploadSchedule(
        @Part file: MultipartBody.Part
    ): Response<Unit>

    // Update user preferences
    @POST("preferences/update")
    suspend fun updatePreferences(
        @Body preferences: SPreferences
    ): Response<Unit>

    // TODO specify your API
    // test
    suspend fun login(
        // TODO
    ): Response<Unit>
}