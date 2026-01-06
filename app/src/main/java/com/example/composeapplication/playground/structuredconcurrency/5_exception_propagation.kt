package com.example.composeapplication.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

fun main()  {

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("caught exception $throwable")
    }

    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

    scope.launch {
        println("Coroutine 1 starts")
        delay(50)
        println("Coroutine 1 fails")
        throw RuntimeException()
    }

    scope.launch {
        println("Coroutine 2 starts")
        delay(500)
        println("Coroutine 2 completed")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 2 was canceled")

        }
    }
    Thread.sleep(1000)
    println("Scope got canceled: ${!scope.isActive}")
}