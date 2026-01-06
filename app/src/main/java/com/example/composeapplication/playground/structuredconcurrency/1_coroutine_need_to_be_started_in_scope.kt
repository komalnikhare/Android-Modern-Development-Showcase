package com.example.composeapplication.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val scope = CoroutineScope(Dispatchers.Default)
fun main() = runBlocking {
   val job =  scope.launch {
        delay(100)
        println("Coroutine completed")
    }
    
    job.invokeOnCompletion { throwable ->
        if (throwable is CancellationException) {
            println("coroutine was canceled!")
        }
    }
    delay(50)
    onDestroy()
}

fun onDestroy(){
    println("coroutine scope ends")
    scope.cancel()
}