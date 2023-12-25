package main.kotlin.com.backend.requests

import kotlinx.serialization.Serializable

@Serializable
data class DeleteNoteRequest(
    val id: String
)