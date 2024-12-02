package com.example.a6starter.data.entities

import com.squareup.moshi.Json
// Making Assumptions Here For Now

data class SPreferences(
    val environment: String,
    val goal: String,
    val locations: List<String>,
    val times: List<String>
)
