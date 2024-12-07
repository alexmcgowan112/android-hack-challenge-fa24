package com.example.a6starter.data.entities

// Making Assumptions Here For Now

data class SPreferences(
    val location_north: Boolean?,
    val location_south: Boolean?,
    val location_central: Boolean?,
    val location_west: Boolean?,
    val time_morning: Boolean?,
    val time_afternoon: Boolean?,
    val time_evening: Boolean?,
    val objective_study: Boolean?,
    val objective_homework: Boolean?
)

// TODO: Move the ones related to different MVVM to different files.

data class UpdatePreferencesResponse(
    val message: String,
    val preferences: PreferencesDetails
)

data class PreferencesDetails(
    val locations: Map<String, Boolean>,
    val times: Map<String, Boolean>,
    val objectives: Map<String, Boolean>
)


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