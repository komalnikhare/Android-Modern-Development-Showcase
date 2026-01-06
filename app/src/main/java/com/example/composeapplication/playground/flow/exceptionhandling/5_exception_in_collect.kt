package com.example.composeapplication.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

suspend fun main() : Unit = coroutineScope {

        val stockFLow = stockFlow()

            stockFLow
                .onCompletion { cause ->
                    if (cause == null){
                        println("Flow completed successfully")
                    } else {
                        println("Flow completed with exception: $cause")
                    }
                }
                .onEach { stock ->
                    throw Exception("Exception in collect{}")
                    println("collected $stock")

                }
                .catch { throwable ->
                    println("Handle exception in catch() operator $throwable")
                }
                .launchIn(this)


}

private fun stockFlow() : Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw Exception("Network request failed!")
}
