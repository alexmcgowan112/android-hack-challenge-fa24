package com.example.a6starter.data.entities


data class newUser(
 val name: String,
 val netid: String,
 val password: String,
 val confirm_password: String
)

data class loginUser(
 val netid: String,
 val password: String
)

// Login Success
data class userResponse(
    val id: Int,
    val name: String,
    val netid: String
)

data class ErrorResponse(
    val error: String
)

data class messageResponse(
    val message: String
)

