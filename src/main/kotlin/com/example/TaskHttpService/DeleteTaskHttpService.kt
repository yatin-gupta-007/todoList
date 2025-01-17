package com.example.TaskHttpService

import com.example.Service.DeleteTaskService
import com.example.utilities.exceptions.TodoTaskException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class DeleteTaskHttpService
@Inject
constructor(
    private val deleteTaskService: DeleteTaskService
) {
    suspend fun invoke(call: ApplicationCall) {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing ID")
                return
            }
            val result = deleteTaskService.invoke(id)
            if (result) {
                call.respond(HttpStatusCode.OK, "Task with ID $id deleted")
            } else {
                call.respond(HttpStatusCode.NotFound, "Task with ID $id not found")
            }
        } catch (e: TodoTaskException) {
            call.respond(HttpStatusCode.BadRequest, e.message.toString())
        }
    }
}