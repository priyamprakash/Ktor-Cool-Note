package main.kotlin.com.backend.collections

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class User(
    val email: String,
    val password: String,
    val id: String = ObjectId().toString()
)
