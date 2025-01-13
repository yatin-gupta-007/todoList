package com.example.Routing

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing() {
        todoListRoutes()
    }
}
