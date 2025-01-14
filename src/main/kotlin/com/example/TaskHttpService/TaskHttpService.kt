import com.example.entities.ToDoRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

suspend fun ApplicationCall.getValidatedId(): Int? {
    val id = parameters["id"]?.toIntOrNull()
    if (id == null) {
        respond(HttpStatusCode.BadRequest, "Invalid or missing ID")
    }
    return id
}

suspend fun ApplicationCall.getValidatedToDoRequest(): ToDoRequest? {
    val request = try {
        receive<ToDoRequest>()
    } catch (e: Exception) {
        respond(HttpStatusCode.BadRequest, "Invalid request body format")
        return null
    }

    if (request.title.isBlank()) {
        respond(HttpStatusCode.BadRequest, "Title cannot be blank")
        return null
    }
    return request
}

suspend fun ApplicationCall.validateId(call: ApplicationCall): Int? {
    val id = call.getValidatedId() ?: return null
    return id
}