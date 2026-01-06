package com.example.composeapplication.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

suspend fun main() : Unit = coroutineScope {

    flow {
        try {
            emit(1)
        } catch (e: Exception) {
            println("Caught exception in flow builder: $e")
            //emit(2)
        }
    }.collect{
        throw Exception("Exception in collect{}")
    }
}