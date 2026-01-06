package com.example.composeapplication.playground.fundamantals

fun main() {
    println("Main Start")
    routine(1, 500)
    routine(2, 300)
    println("Main End")
}

fun routine(number: Int, delay: Long){
    println("Routine $number start")
    Thread.sleep(delay)
    println("Routine $number end")
}