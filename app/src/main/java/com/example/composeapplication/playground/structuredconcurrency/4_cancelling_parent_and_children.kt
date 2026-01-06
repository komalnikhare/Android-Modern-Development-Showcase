package com.example.composeapplication.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)

    scope.coroutineContext[Job]!!.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Parent scope was canceled")
        }
    }
    val childCoroutineJob = scope.launch {
        delay(1000)
        println("Coroutine 1 completed")
    }

    childCoroutineJob.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 1 was canceled")
        }
    }

    scope.launch {
        delay(1000)
        println("Coroutine 2 completed")
    }.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("Coroutine 2 was canceled")
        }
    }

    delay(100)
    childCoroutineJob.cancelAndJoin()
    //scope.coroutineContext[Job]!!.cancelAndJoin()
}