package com.example.Database

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/todolist",
        user = "yatin.gupta",
        driver = "org.postgresql.Driver",
        password = "",
    )

    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_READ_COMMITTED
    transaction {
        SchemaUtils.create(ToDoEntity)
    }
}
