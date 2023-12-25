package main.kotlin.com.backend.routes


import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import main.kotlin.com.backend.collections.Note
import main.kotlin.com.backend.data.getNotesForUser

fun Route.noteRoutes() {
    route("/getNotes") {
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val notes = getNotesForUser(email)
                if (notes.isEmpty()) {
                    call.respond(OK, emptyList<Note>().toList())
                } else {
                    call.respond(OK, notes)
                }
            }
        }
    }
}
