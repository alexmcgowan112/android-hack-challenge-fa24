package com.example.a6starter.data.remote

import com.example.a6starter.data.entities.DogEntity
import com.example.a6starter.data.entities.SPreferences
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("preferences")
    suspend fun getStudentPreferences(
        @Query("student[id]") netId: Int
    ): Response<SPreferences>
    // TODO specify your API
    //  This time you will want your function to take in a parameter for the page number, since we
    //  are dealing with paginated data.
    //  Annotate the parameter with @Query("page[number]") to tell Retrofit how to put the parameter
    //  in the API Request.
    // test
}