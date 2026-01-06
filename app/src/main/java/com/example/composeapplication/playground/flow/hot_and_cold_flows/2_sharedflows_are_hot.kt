package com.example.composeapplication.playground.flow.hot_and_cold_flows

import io.ktor.client.utils.clientDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun main() {

    val sharedFlow = MutableSharedFlow<Int>()
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        repeat(5){
            println("SharedFlow emits $it")
            sharedFlow.emit(it)
            delay(200)
        }
    }

    scope.launch {
        sharedFlow.collect{
            println("Collected 1 $it")
        }
    }

    scope.launch {
        sharedFlow.collect{
            println("Collected 2 $it")
        }
    }

    Thread.sleep(1500)
}