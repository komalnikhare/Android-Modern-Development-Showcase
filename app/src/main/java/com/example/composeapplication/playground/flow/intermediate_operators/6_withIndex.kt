package com.example.composeapplication.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.withIndex

suspend fun main() {

    flowOf(1,2,3,4,5)
        .withIndex()
        .collect{ collectedValue ->
                println(collectedValue)
        }



}