package com.example.composeapplication.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

suspend fun main() : Unit = coroutineScope {

    flow {
        emit(1)
        emit(2)
        emit(3)
    }.collect{
        println("Collected $it")
    }
}

val inlineFlow = flow<Int> {
    println("Collected 1")
    println("Collected 2")
    println("Collected 3")
}