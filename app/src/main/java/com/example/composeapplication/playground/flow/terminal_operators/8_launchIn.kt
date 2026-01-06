package com.example.composeapplication.playground.flow.terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext

fun main() {

    val flow = flow {
        delay(100)

        println("Emitting first value")
        emit(1)

        delay(100)

        println("Emitting second value")
        emit(2)

    }

    val scope = CoroutineScope(EmptyCoroutineContext)

    flow
        .onEach { println("Received $it with launchIn() - 1") }
        .launchIn(scope)

    flow
        .onEach { println("Received $it with launchIn() - 2") }
        .launchIn(scope)

    /*scope.launch {
        flow.collect{
            println("Received $it in collect -1")
        }
        flow.collect{
            println("Received $it in collect -2")
        }
    }*/

    Thread.sleep(1000)
}