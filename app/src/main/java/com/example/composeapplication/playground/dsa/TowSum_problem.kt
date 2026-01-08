package com.example.composeapplication.playground.dsa

/*
Given an array of integers nums and an integer target, return indices of the two numbers
 such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use
the same element twice.

You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]*/

fun towSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()

    for ((index, num) in nums.withIndex()){
        val comp = target - num
        if(map.containsKey(comp))
            return intArrayOf(map[comp]!!, index)
        map[num] = index
    }

    throw IllegalArgumentException("No two sum solution")
}

fun towSum2(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices){
        for (j in i + 1 until nums.size){
            if(nums[i] + nums[j] == target)
                return intArrayOf(i, j)
        }
    }
    throw IllegalArgumentException("No two sum solution")
}

fun main(){
    val nums = intArrayOf(2,7,11,15)
    val target = 13
    val result = towSum(nums, target)
    println("Indices: [${result[0]}, ${result[1]}]")
}