package com.example.composeapplication.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main() : Unit = coroutineScope {

    launch {
        val stockFLow = stockFlow()

            stockFLow
                .onCompletion { cause ->
                    if (cause == null){
                        println("Flow completed successfully")
                    } else {
                        println("Flow completed with exception: $cause")
                    }
                }
                .catch { throwable ->
                    println("Handle exception in catch() operator $throwable")
                }
                .collect{ stock ->
                    println("collected $stock")
            }
    }

}

private fun stockFlow() : Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw Exception("Network request failed!")
}