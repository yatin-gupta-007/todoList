package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    val id: Int,
    val title: String,
    val isDone: Boolean
)

