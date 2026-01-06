package com.example.composeapplication.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf

suspend fun main() {

    flowOf(1,1,2,3,4,4,5)
        .distinctUntilChanged()
        .collect{ collectedValue ->
                println(collectedValue)
        }



}