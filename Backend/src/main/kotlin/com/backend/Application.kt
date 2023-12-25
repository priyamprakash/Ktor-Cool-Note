package com.backend

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.auth.*
import kotlinx.serialization.json.Json
import main.kotlin.com.backend.data.checkPasswordForEmail
import main.kotlin.com.backend.plugins.configureRouting


fun main() {
    embeddedServer(Netty, port = 8001, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    install(Authentication) {
        basic {
            realm = "Cool Note Server"
            validate { credentials ->
                val email = credentials.name
                val password = credentials.password
                if (checkPasswordForEmail(email, password)) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
    configureRouting()


}

