package com.example.composeapplication.playground.flow.stateflow

import androidx.compose.material3.adaptive.calculatePosture
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

suspend fun main() {

    val counter = MutableStateFlow(0)

    println(counter.value)

    coroutineScope {
        launch {
            repeat(10_000){
                counter.update { currentValue ->
                    currentValue + 1
                }
            }
        }
    }

    println(counter.value)
}