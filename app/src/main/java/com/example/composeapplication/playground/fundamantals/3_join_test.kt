package com.example.composeapplication.playground.fundamantals

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val scope = CoroutineScope(Dispatchers.Default)

    val coroutine1 = scope.launch {

        println("coroutine 1 started")
        delay(1000)
        println("coroutine 1 competed")
    }
    val coroutine2 = scope.launch {
        println("coroutine 2 started")
        delay(1000)
        println("coroutine 2 competed")
    }

    coroutine1.join()
    coroutine2.join()

    val coroutine3 = scope.launch {
        println("coroutine 3 started")
        delay(1000)
        println("coroutine 3 competed")
    }

    println("All coroutines completed")

}