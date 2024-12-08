package com.example.a6starter.data.entities

data class matchesFound(
    val matches: List<matchInfo>
)

data class matchInfo(
    val name: String,
    val netid: String,
    val match_score: Float,
    val common_courses: List<String>,
    val common_preferences: commonPref
)

data class commonPref(
    val locations: List<String>,
    val times: List<String>,
    val objectives: List<String>
)

data class EmailRequest(
    val sender_netid: String
)