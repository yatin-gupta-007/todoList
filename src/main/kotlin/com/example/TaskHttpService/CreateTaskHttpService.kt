package com.example.TaskHttpService

import com.example.Service.CreateTaskService
import com.example.entities.ToDoRequest
import com.example.utilities.exceptions.TodoTaskException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class CreateTaskHttpService
@Inject
constructor(
    private val createTaskService: CreateTaskService
) {
    suspend fun invoke(call: ApplicationCall) {
        try {
            val request = call.receive<ToDoRequest>()
            if (request.title == null) {
                call.respond(HttpStatusCode.BadRequest, "Title or Description is missing")
                return
            }
            val result = createTaskService.invoke(request)
            if (result != null) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to create task")
            }
        } catch (e: TodoTaskException) {
            call.respond(HttpStatusCode.BadRequest, e.message.toString())
        }
    }
}