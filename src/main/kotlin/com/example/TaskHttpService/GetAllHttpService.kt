package com.example.TaskHttpService

import com.example.Service.GetAllTaskService
import com.example.utilities.exceptions.TodoTaskException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import javax.inject.Inject

class GetAllHttpService
@Inject
constructor(
    private val getAllTaskService: GetAllTaskService
) {
    suspend fun invoke(call: ApplicationCall) {
        try {
            val response = getAllTaskService.invoke()
            call.respond(HttpStatusCode.OK, response ?: listOf())
        } catch (e: TodoTaskException) {
            call.respond(HttpStatusCode.BadRequest, e.message.toString())
        }
    }
}