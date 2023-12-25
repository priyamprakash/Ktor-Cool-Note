package main.kotlin.com.backend.requests

import kotlinx.serialization.Serializable

@Serializable
data class AccountRequest(
    val email: String,
    val password: String
)