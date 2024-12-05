package com.example.a6starter.data.entities

import com.squareup.moshi.Json
// Making Assumptions Here For Now

data class SPreferences(
    val review: Boolean,
    val homework: Boolean,
    val morning: Boolean,
    val afternoon: Boolean,
    val evening: Boolean,
    val central: Boolean,
    val north: Boolean,
    val west: Boolean,
    val collegeTown: Boolean
)

data class SAccountCreate(
    val name: String,
    val netId: String,
    val password: String,
    val confirmPassword: String
)

data class SFile(
    val file: String
)