package com.example.composeapplication.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch

suspend fun main() : Unit = coroutineScope {

    launch {
        stockFlow()
            .catch { throwable ->
                println("Handle exception in catch() operator $throwable")
            }
            .collect{ stockData ->
                println("collected $stockData")
            }
    }
}

private fun stockFlow() : Flow<String> = flow {

    repeat(5) { index ->
        delay(1000)

        if (index < 4) {
            emit("New stock data!")
        }else {
            throw NetworkException("Network request failed!")
        }
    }
}//.retry(retries = 3)
    .retryWhen{ cause, attempt ->
        println("Enter retry() with $cause")
        delay(1000 * (attempt +1))
        if(attempt <3)
            cause is NetworkException
        else false
}

class NetworkException(message: String): Exception(message)