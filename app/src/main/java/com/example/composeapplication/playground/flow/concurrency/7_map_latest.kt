package com.example.composeapplication.playground.flow.concurrency

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

suspend fun main() = coroutineScope {

    val flow = flow {
        repeat(5) {
            val pancakeIndex = it+1
            println("Emitter:  Start Cooking pancake $pancakeIndex")
            kotlinx.coroutines.delay(100)
            println("Emitter:  Pancake $pancakeIndex ready!")
            emit(pancakeIndex)
        }
    }.mapLatest {
        println("Add topping into the  pancake $it")
        delay(200)
        it
    }

    flow.collect{
        println("Collector:  Start Eating pancake $it")
        kotlinx.coroutines.delay(300)
        println("Collector:  Finish eating pancake $it")
    }
}