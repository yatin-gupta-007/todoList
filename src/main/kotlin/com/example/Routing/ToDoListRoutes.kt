package com.example.Routing

import com.example.Repository.ToDoRepository
import com.example.Repository.ToDoRepositoryImpl
import com.example.entities.ToDoRequest
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.todoListRoutes() {
    val repository: ToDoRepository = ToDoRepositoryImpl()
    route("/tasks") {
        put("/update-task/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing ID")
                return@put
            }

            val request = call.receive<ToDoRequest>() // Deserialize request body into UpdateTaskRequest
            if (request.title.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Invalid request body")
                return@put
            }

            val task = repository.updateTask(id, request.title, request.isDone)
            call.respond(HttpStatusCode.OK, task)
        }
        get("/") {
            call.respondText("To Do List!")
        }

        get("/getAllTasks") {
            call.respond(repository.getAllTasks())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            } else {
                val task = repository.getTaskById(id)
                if (task == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(task)
                }
            }
        }

        post("/add-task") {
            val toDo = call.receive<ToDoRequest>()
            val task = repository.createTask(toDo)
            call.respond(HttpStatusCode.Created, task)
        }

        delete("/delete-task/{id}") {
            val id = call.parameters["id"]?.toInt()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                repository.deleteTask(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}