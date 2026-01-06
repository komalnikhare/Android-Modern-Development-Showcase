package com.example.composeapplication.playground.fundamantals

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    println("Main Start")
    joinAll(
        async { coroutine(1, 500) },
        async { coroutine(2, 300) }
    )
    println("Main End")
}

suspend fun coroutine(number: Int, delay: Long) {
    println("coroutine $number start")
    delay(delay)
    println("coroutine $number end")
}