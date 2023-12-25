package com.backend

import com.backend.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import main.kotlin.com.backend.collections.User
import main.kotlin.com.backend.data.registerUser


fun main() {
    embeddedServer(Netty, port = 8001, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    install(DefaultHeaders)
    install(CallLogging)
//    install(Routing)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }

    CoroutineScope(Dispatchers.IO).launch {
        registerUser(
            User(
                "abc@2803.com",
                "123456"
            )
        )
    }


}
