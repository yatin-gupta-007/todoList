package com.example.ComponentFactory

import com.example.di.DaggerTaskHttpComponent
import com.example.di.TaskHttpComponent
import com.example.di.RootComponent

class ComponentFactory(private val rootComponent: RootComponent) {
    val taskHttpComponent: TaskHttpComponent = DaggerTaskHttpComponent.builder()
        .rootComponent(rootComponent)
        .build()
}