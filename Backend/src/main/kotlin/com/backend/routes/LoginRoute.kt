package main.kotlin.com.backend.routes


import  io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import main.kotlin.com.backend.data.checkPasswordForEmail
import main.kotlin.com.backend.requests.AccountRequest
import main.kotlin.com.backend.responses.SimpleResponse

fun Route.loginRoute() {
    route("/login") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch(e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isPasswordCorrect = checkPasswordForEmail(request.email, request.password)
            if(isPasswordCorrect) {
                call.respond(OK, SimpleResponse(true, "Your are now logged in!"))
            } else {
                call.respond(OK, SimpleResponse(false, "The E-Mail or password is incorrect"))
            }
        }
    }
}












