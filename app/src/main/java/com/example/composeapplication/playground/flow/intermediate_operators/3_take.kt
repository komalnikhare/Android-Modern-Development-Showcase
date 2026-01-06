package com.example.composeapplication.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile

suspend fun main() {

   /* flowOf(1,2,3,4,5)
        .take(3)
        .collect{ collectedValue ->
                println(collectedValue)
        }*/

    flowOf(1,2,3,4,5)
        .takeWhile { it < 3 }
        .collect{ collectedValue ->
            println(collectedValue)
        }


}