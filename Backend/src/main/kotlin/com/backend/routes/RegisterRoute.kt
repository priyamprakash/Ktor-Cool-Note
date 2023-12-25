package main.kotlin.com.backend.routes


import io.ktor.http.*
import main.kotlin.com.backend.responses.SimpleResponse

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import main.kotlin.com.backend.collections.User
import main.kotlin.com.backend.data.checkIfUserExists
import main.kotlin.com.backend.data.registerUser
import main.kotlin.com.backend.requests.AccountRequest

fun Route.registerRoute() {
    route("/register") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch(e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            println("Received request: $request")


            val userExists = checkIfUserExists(request.email)
            if(!userExists) {
                if(registerUser(User(request.email, request.password))) {
                    call.respond(OK, SimpleResponse(true, "Successfully created account!"))
                } else {
                    call.respond(OK, SimpleResponse(false, "An unknown error occurred"))
                }
            } else {
                call.respond(OK, SimpleResponse(false, "A user with that E-Mail already exists"))
            }
        }
    }
}