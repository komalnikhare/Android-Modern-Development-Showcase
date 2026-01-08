package com.example.composeapplication.playground.dsa

/*
* Given an integer array nums, return true if any value appears at least twice
* in the array, and return false if every element is distinct.
* */

fun containsDuplicate(numbs: IntArray): Boolean {
    val set = mutableSetOf<Int>()

    for (num in numbs){
        if(!set.add(num)) return true
    }
    return false
}

fun main(){
    val nums = intArrayOf(1,2,3,4,5,1)
    val result = containsDuplicate(nums)
    println("Contains Duplicate: $result")
}