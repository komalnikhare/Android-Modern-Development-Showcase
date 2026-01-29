package com.example.composeapplication.playground.dsa

/*
    Given two strings s and t, return true if t is an anagram of s, and false otherwise.

    Example 1:
    Input: s = "anagram", t = "nagaram"
    Output: true

    Example 2:
    Input: s = "rat", t = "car"
    Output: false
 */
fun isAnagramSort(s: String, t: String): Boolean {
    val a = s.filterNot { it.isWhitespace() }.lowercase()
    val b = t.filterNot { it.isWhitespace() }.lowercase()
    return a.length == b.length && a.toCharArray().sorted() == b.toCharArray().sorted()
}

fun isAnagramMap(s: String, t: String): Boolean {
    val a = s.filterNot { it.isWhitespace() }.lowercase()
    val b = t.filterNot { it.isWhitespace() }.lowercase()
    if (a.length != b.length) return false

    val map = mutableMapOf<Char, Int>()
    for (c in a) map[c] = map.getOrDefault(c, 0) +1
    for(c in b) {
        val count = map[c] ?: return false
        if(count == 1) map.remove(c)
        else map[c] = count -1
    }
    return map.isEmpty()
}

fun main(){
    val s1 = "Listen"
    val s2 = "Silent"

    println("Sorted Method: ${isAnagramSort(s1, s2)}")
    println("Map Method: ${isAnagramMap(s1, s2)}")
}

