package main.kotlin.com.backend.responses

import kotlinx.serialization.Serializable


@Serializable
data class SimpleResponse(
    val successful: Boolean,
    val message: String
)