package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class ToDoRequest (
    val title: String,
    val isDone: Boolean
)