package com.example.a6starter.data.entities

// Get student preferences (GET request for student preferences)
data class GPreferences(
    val north: Boolean,
    val south: Boolean,
    val central: Boolean,
    val west: Boolean,
    val morning: Boolean,
    val afternoon: Boolean,
    val evening: Boolean,
    val study: Boolean,
    val homework: Boolean
)