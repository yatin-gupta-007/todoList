package com.example

import com.example.Database.configureDatabases
import com.example.Routing.configureRouting
import com.example.Routing.configureSerialization
import com.example.utilities.configureHTTP
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureHTTP()
    configureRouting()
}
