package com.example.composeapplication.playground.flow.concurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow

suspend fun main() = coroutineScope {

    val flow = flow {
        repeat(5) {
            val pancakeIndex = it+1
            println("Emitter:  Start Cooking pancake $pancakeIndex")
            kotlinx.coroutines.delay(100)
            println("Emitter:  Pancake $pancakeIndex ready!")
            emit(pancakeIndex)
        }
    }.buffer()

    flow.collect{
        println("Collector:  Start Eating pancake $it")
        kotlinx.coroutines.delay(300)
        println("Collector:  Finish eating pancake $it")
    }
}