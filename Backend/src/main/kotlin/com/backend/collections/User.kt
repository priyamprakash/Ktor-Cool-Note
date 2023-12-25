package main.kotlin.com.backend.collections

import org.bson.types.ObjectId

data class User(
    val email: String,
    val password: String,
    val id: String = ObjectId().toString()
)
