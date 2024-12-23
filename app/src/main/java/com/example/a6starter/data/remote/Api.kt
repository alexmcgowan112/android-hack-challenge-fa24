package com.example.a6starter.data.remote

import com.example.a6starter.data.entities.EmailRequest
import com.example.a6starter.data.entities.GPreferences
import com.example.a6starter.data.entities.SPreferences
import com.example.a6starter.data.entities.UpdatePreferencesResponse
import com.example.a6starter.data.entities.loginUser
import com.example.a6starter.data.entities.matchesFound
import com.example.a6starter.data.entities.messageResponse
import com.example.a6starter.data.entities.newUser
import com.example.a6starter.data.entities.userResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface Api {
    // Upload schedule (POST request for .ics file)
    @POST("upload")
    @Multipart
    suspend fun uploadSchedule(
        @Part file: MultipartBody.Part
    ): Response<messageResponse>

    // Update user preferences
    @POST("user/preferences")
    suspend fun updatePreferences(
        @Body preferences: SPreferences
    ): Response<UpdatePreferencesResponse>

    @POST("create")
    suspend fun createUser(
        @Body newUser: newUser
    ): Response<userResponse>

    @POST("login")
    suspend fun loginUser(
        @Body loginRequest: loginUser
    ): Response<userResponse>

    @GET("users/{netid}")
    suspend fun getUser(
        @Path("netid") netid: String
    ): Response<userResponse>

    // Some extra brain juice is needed for this one.
    // Do we need to store the current user.
    @POST("logout")
    suspend fun logoutUser(
    ): Response<messageResponse>

    @POST("send-email")
    suspend fun sendEmail(
        @Body emailRequest: EmailRequest
    ): Response<messageResponse>

    @GET("search")
    suspend fun searchResults(): Response<matchesFound>

    @GET("preferences")
    suspend fun getPreferences(
    ): Response<GPreferences>




}