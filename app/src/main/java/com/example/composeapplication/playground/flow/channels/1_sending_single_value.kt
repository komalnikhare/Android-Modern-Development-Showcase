package com.example.composeapplication.playground.flow.channels

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() : Unit = coroutineScope {

    val deffered = async {
        delay(100)
        10
    }

    launch {
        val result = deffered.await()
        println("Result is $result")
    }
}