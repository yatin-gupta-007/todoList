package com.example.TaskHttpService

import com.example.Service.UpdateTaskService
import com.example.entities.ToDoRequest
import com.example.utilities.exceptions.TodoTaskException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class UpdateTaskHttpService
@Inject
constructor(
    private val updateTaskService: UpdateTaskService
) {
    suspend fun invoke(call: ApplicationCall) {
        try {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing ID")
                return
            }
            val request = call.receive<ToDoRequest>()
            val result = updateTaskService.invoke(id, request)
            if (result != null) {
                call.respond(HttpStatusCode.OK, result)
            } else {
                call.respond(HttpStatusCode.NotFound, "Task with ID $id not found")
            }
        }catch (e : TodoTaskException){
            call.respond(HttpStatusCode.BadRequest, e.message.toString())
        }
    }
}