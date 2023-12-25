package main.kotlin.com.backend.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import main.kotlin.com.backend.routes.registerRoute

fun Application.configureRouting() {
    routing {
        registerRoute()
    }
}
