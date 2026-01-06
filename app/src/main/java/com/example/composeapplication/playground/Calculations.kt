package com.example.composeapplication.playground

fun main(){
    var string = "7508504302"
    val result = string.map { it.toString().toInt() }.count { it % 2 == 0 }
    val result2 = string.map { it.toString().toInt() }.filter { it % 2 == 0 }.sum()
    println("Result: $result Result2: $result2")
}