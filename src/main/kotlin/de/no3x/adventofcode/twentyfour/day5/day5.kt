package de.no3x.adventofcode.twentyfour.day5

data class Input(
    val rules: List<Pair<Int, Int>>,
    val updates: List<List<Int>>
)

class Day5 {

    fun solve(input: Input): Int {
        return input.rules.size
    }
}
