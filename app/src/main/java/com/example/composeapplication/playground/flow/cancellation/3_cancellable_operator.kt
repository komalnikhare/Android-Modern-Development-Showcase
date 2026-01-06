package com.example.composeapplication.playground.flow.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() {
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        flowOf(1,2,3)
            .onCompletion { throwable ->
                if (throwable == CancellationException()) {
                    println("Flow was cancelled")
                }
            }
            .cancellable()
            .collect {
                println("Collected: $it")
                if (it == 2) {
                  cancel()
                }
        }
    }.join()
}


private suspend fun calculateFactorialOf(number: Int): BigInteger = coroutineScope {
    var factorial = BigInteger.ONE
    for (i in 1..number) {

        factorial *= BigInteger.valueOf(i.toLong())
        ensureActive()
    }
     factorial
}