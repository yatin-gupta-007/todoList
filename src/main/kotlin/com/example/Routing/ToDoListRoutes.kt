package com.example.Routing

import com.example.Database.ToDoRepositoryImpl
import com.example.Repository.ToDoRepository
import com.example.Service.TaskService
import com.example.entities.ToDoRequest
import getValidatedId
import getValidatedToDoRequest
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import validateId

fun Route.todoListRoutes() {
    val repository: ToDoRepository = ToDoRepositoryImpl()
    val taskService = TaskService(repository)
    route("/tasks") {
        put("/update-task/{id}") {
            val id = call.getValidatedId() ?: return@put
            val request = call.getValidatedToDoRequest() ?: return@put
            val task = taskService.updateTask(id, request.title, request.isDone)
            call.respond(HttpStatusCode.OK, task)
        }
        get("/") {
            call.respondText("To Do List!")
        }

        get("/getAllTasks") {
            call.respond(repository.getAllTasks())
        }

        get("/{id}") {
            val id = call.validateId(call) ?: return@get
            val task = taskService.getTaskById(id)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound, "Task with ID $id not found")
            } else {
                call.respond(task)
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