package com.example.a6starter.data.entities

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