package com.example.composeapplication.playground.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() {

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught $throwable in CoroutineExceptionHandler")
    }

    val scope = CoroutineScope(Job() + exceptionHandler)

    scope.launch {
        try {
            supervisorScope {
                launch {
                    println("CHI: ${coroutineContext[CoroutineExceptionHandler]}")
                    throw RuntimeException()
                }
            }
        } catch (e: Exception) {
            println("Caught: $e")
        }
    }

}