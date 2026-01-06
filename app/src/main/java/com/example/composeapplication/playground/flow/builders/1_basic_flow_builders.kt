package com.example.composeapplication.playground.flow.builders

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


suspend fun main() {
    val firstFlow = flowOf<Int>(1).collect{ emittedValue ->
        println("First Flow: $emittedValue")
    }

    val secondFlow = flowOf(1,2,3)

        secondFlow.collect { emittedValue ->
        println("Second Flow: $emittedValue")
    }

    listOf("A","B","C").asFlow().collect{
            emittedValue ->
        println("List Flow: $emittedValue")
    }

    flow {
        kotlinx.coroutines.delay(2000)
        emit("Item emitted after 2000ms")

        emitAll(secondFlow)
    }.collect{
            emittedValue ->
        println(" flow(): $emittedValue")
    }
}