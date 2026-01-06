package com.example.composeapplication.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

suspend fun main() : Unit = coroutineScope {

    launch {
        val stockFLow = stockFlow()
            .map {
                throw Exception("Exception in map")
            }

        try {
            stockFLow
                .onCompletion { cause ->
                    if (cause == null){
                        println("Flow completed successfully")
                    } else {
                        println("Flow completed with exception: $cause")
                    }
                }
                .collect{ stock ->
                println("collected $stock")
            }
        } catch (e: Exception) {
            println("Handle exception in catch block $e")
        }
    }

}

private fun stockFlow() : Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw Exception("Network request failed!")
}