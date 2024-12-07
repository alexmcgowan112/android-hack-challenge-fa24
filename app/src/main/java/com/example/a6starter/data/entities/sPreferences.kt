package com.example.a6starter.data.entities

// Sending student preferences (POST request for student preferences)
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
) {
    constructor(pref: GPreferences) : this(
        location_north = pref.north,
        location_south = pref.south,
        location_central = pref.central,
        location_west = pref.west,
        time_morning = pref.morning,
        time_afternoon = pref.afternoon,
        time_evening = pref.evening,
        objective_study = pref.study,
        objective_homework = pref.homework
    )
}

data class UpdatePreferencesResponse(
    val message: String,
    val preferences: PreferencesDetails
)

data class PreferencesDetails(
    val locations: Map<String, Boolean>,
    val times: Map<String, Boolean>,
    val objectives: Map<String, Boolean>
)