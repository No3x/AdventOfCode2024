package de.no3x.adventofcode.twentyfour.day2

import kotlin.math.abs


class Day2 {

    fun solve(list1: List<List<Int>>): Int {
        return list1.count(this::areAllPositiveOrAllNegativeAndDecreasingBetween1And3)
    }

    fun areAllPositiveOrAllNegativeAndDecreasingBetween1And3(ints: List<Int>): Boolean {
        val subtrahends = ints
            .zipWithNext { a, b -> a - b }
        return (subtrahends.all { it > 0 } || subtrahends.all { it < 0 })
                && subtrahends.all { abs(it) in 1..3 }
    }
}

