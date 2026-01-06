package com.example.composeapplication.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    val coroutineJob = scope.launch {
        println("coroutine started")
        delay(1000)
    }

    Thread.sleep(1000)


    println("coroutineJob is child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
}