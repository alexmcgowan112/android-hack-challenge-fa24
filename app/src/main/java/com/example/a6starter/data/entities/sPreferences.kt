package com.example.a6starter.data.entities

import com.squareup.moshi.Json
// Making Assumptions Here For Now

data class SPreferences(
    val review: Boolean?,
    val homework: Boolean?,
    val morning: Boolean?,
    val afternoon: Boolean?,
    val evening: Boolean?,
    val Central_campus: Boolean?,
    val North_campus: Boolean?,
    val West_campus: Boolean?,
    val collegeTown: Boolean?
)

// TODO: Move the ones related to different MVVM to different files.

data class SAccountCreate(
    val name: String,
    val netId: String,
    val password: String,
    val confirmPassword: String
)

data class SAccountLogin(
    val netId: String,
    val password: String
)