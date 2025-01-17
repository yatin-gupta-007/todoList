package com.example

import com.example.ComponentFactory.ComponentFactory
import com.example.Routing.configureRouting
import com.example.Routing.configureSerialization
import com.example.di.DaggerRootComponent
import com.example.modules.DatabaseModule
import com.example.utilities.configureHTTP
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val rootComponent = DaggerRootComponent.create()
    val httpComponent = ComponentFactory(rootComponent).taskHttpComponent
    val database: Database = rootComponent.database
    println("Connected to database: ${database.url}")
    configureSerialization()
    configureHTTP()
    configureRouting(httpComponent)
}
