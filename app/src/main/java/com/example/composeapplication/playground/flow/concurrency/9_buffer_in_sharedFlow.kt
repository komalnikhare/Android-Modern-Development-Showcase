package com.example.composeapplication.playground.flow.concurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val flow = MutableSharedFlow<Int>(extraBufferCapacity = 10)

    //Collector1
    launch {
        flow.collect{
            println("Collector1: $it")
        }
    }

    //Collector2
    launch {
        flow.collect{
            println("Collector2: $it")
            delay(100)
        }
    }

    //Emitter
    launch {
            repeat(5){
                flow.emit(it)
                delay(10)
            }
    }

}