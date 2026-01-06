package com.example.composeapplication.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

suspend fun main() {

    flowOf(1,2,3,4,5)
        .filter { it > 3 }
        .collect{ collectedValue ->
                println(collectedValue)
        }

    flowOf(1,2,3,4,5)
        .filterIsInstance<Int>()
        .collect{ collectedValue ->
            println(collectedValue)
        }
}