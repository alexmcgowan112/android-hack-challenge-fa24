package com.example.a6starter.data.entities

data class SearchResult(
    val matches: List<StudyMatch>
)

data class StudyMatch(
    val name: String,
    val netid: String,
    val match_score: Float,
    val common_courses: List<String>,
    val common_preferences: CommonPreferences
)

data class CommonPreferences(
    val locations: List<String>,
    val times: List<String>,
    val objectives: List<String>
)