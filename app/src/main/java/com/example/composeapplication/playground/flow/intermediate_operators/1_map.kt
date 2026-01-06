package com.example.composeapplication.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

suspend fun main() {

    flowOf(1,2,3,4,5)
        .map { it * 10 }
        .collect{ collectedValue ->
                println(collectedValue)
        }
}