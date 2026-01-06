package com.example.composeapplication.playground.flow.hot_and_cold_flows

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

fun coldFlow() = flow {
    println("Emitting 1")
    emit(1)

    delay(1000)
    println("Emitting 2")
    emit(2)

    delay(1000)
    println("Emitting 3")
    emit(3)
}

suspend fun main(): Unit = coroutineScope {

    //Example 1
   /* val job = launch {
        coldFlow()
            .onCompletion {
                println("Flow completed for Collector 1")
            }
            .collect {
                println("Collector 1 collects: $it")
            }
    }

    delay(1500)
    job.cancelAndJoin()*/

    //Example 2
    launch {
        coldFlow()
            .collect {
                println("Collector 1 collects: $it")
            }
    }

    launch {
        coldFlow()
            .collect {
                println("Collector 2 collects: $it")
            }
    }
}