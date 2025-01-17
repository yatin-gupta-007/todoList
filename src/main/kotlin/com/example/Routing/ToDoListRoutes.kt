package com.example.Routing

import com.example.di.TaskHttpComponent
import io.ktor.server.routing.*

fun Route.todoListRoutes(httpComponent: TaskHttpComponent) {
    route("/tasks") {
        put("/update-task/{id}") { httpComponent.updateTaskHttpService.invoke(call) }
        get("/getAllTasks") { httpComponent.getAllHttpService.invoke(call) }
        get("/{id}") { httpComponent.getTaskHttpService.invoke(call) }
        post("/add-task") { httpComponent.createTaskHttpService.invoke(call) }
        delete("/delete-task/{id}") { httpComponent.deleteTaskHttpService.invoke(call) }
    }
}