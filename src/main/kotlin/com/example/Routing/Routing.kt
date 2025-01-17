package com.example.Routing

import com.example.di.TaskHttpComponent
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(httpComponent: TaskHttpComponent) {
    routing() {
        get("/") {
            call.respondText("TO DO LIST!")
        }
        todoListRoutes(httpComponent)
    }
}
