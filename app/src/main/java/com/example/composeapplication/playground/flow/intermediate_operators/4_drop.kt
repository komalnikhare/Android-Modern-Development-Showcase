package com.example.composeapplication.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take

suspend fun main() {

    flowOf(1,2,3,4,5)
        .drop(3)
        .collect{ collectedValue ->
                println(collectedValue)
        }
    flowOf(1,2,3,4,5)
        .dropWhile { it < 2 }
        .collect{ collectedValue ->
            println(collectedValue)
        }


}