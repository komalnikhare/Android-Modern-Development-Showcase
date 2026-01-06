package com.example.composeapplication.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        repeat(10) { index ->
            //ensureActive()
            //yield()
            if (isActive) {
                println("Operation number: $index")
                Thread.sleep(100)
            } else{
                println("Cleaning up..")
                throw CancellationException()
            }
        }
    }

    delay(250)
    println("Cancelling coroutine")
    job.cancel()

}