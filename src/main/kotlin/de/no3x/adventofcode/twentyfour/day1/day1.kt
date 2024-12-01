package de.no3x.adventofcode.twentyfour.day1

import kotlin.math.abs

class Day1 {
    fun distance(list1: List<Int>, list2: List<Int>): Int {
        return list1.sorted().zip(list2.sorted()).sumOf {
            abs(it.first - it.second)
        }
    }
}