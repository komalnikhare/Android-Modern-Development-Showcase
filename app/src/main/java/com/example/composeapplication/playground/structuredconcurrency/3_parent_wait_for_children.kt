package com.example.composeapplication.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val scope = CoroutineScope(Dispatchers.Default)

   val parentCoroutine =  scope.launch {
        launch {
            delay(1000)
            println("Child Coroutine 1 completed")
        }

        launch {
            delay(1000)
            println("Child Coroutine 2 completed")
        }
    }

    parentCoroutine.join()
    println("ParentCoroutine has been completed")

}