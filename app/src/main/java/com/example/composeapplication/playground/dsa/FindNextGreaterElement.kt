package com.example.composeapplication.playground.dsa

/*
    Given an integer array nums, for each element find the next greater element to its right.
     Return an array where each position contains the next greater element or -1 if none exists.

     Example 1:
     Input: nums = [4, 5, 2, 25]
     Output: [5, 25, 25, -1]

     Example 2:
     Input: nums = [4,1,2]
     Output: [-1, 2, -1]
 */

fun nextGreaterElements(nums: IntArray): IntArray {

    val result = IntArray(nums.size){-1}
    val stack = ArrayDeque<Int>()

    for (i in nums.indices){
        while (stack.isNotEmpty() && nums[i] > nums[stack.last()]){
            val index = stack.removeLast()
            result[index] = nums[i]
        }
        stack.addLast(i)
    }
    return result
}

fun main() {
    val a = intArrayOf(4,1,2)
    println("NGE: ${nextGreaterElements(a).joinToString(", ")}") // [5, 25, 25, -1]


}
