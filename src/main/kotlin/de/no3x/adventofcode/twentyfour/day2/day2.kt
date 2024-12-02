package de.no3x.adventofcode.twentyfour.day2


class Day2 {

    fun solve(list1: List<List<Int>>): Int {
        return list1.count(::`areDecreasingBetween1And3And(AllPositiveOrAllNegative)`)
    }

    fun `areDecreasingBetween1And3And(AllPositiveOrAllNegative)`(ints: List<Int>): Boolean {
        val subtractions = ints
            .zipWithNext { a, b -> a - b }
        return (subtractions.all { it > 0 } || subtractions.all { it < 0 }) && subtractions
            .all { it in 1..3 || it in -1 downTo -3 }
    }
}

