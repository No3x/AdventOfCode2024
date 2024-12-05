package de.no3x.adventofcode.twentyfour.day5

import kotlin.math.ceil

data class Input(
    val rules: List<Pair<Int, Int>>, val updates: List<List<Int>>
)

class Day5 {

    fun solve(input: Input): Int {

        val result = input.updates.filterIndexed { index, update ->
            val isValid = isValidUpdate(update, input.rules)
            println("Update ${index + 1}: ${if (isValid) "Valid" else "Invalid"}")
            isValid
        }.sumOfTheirMiddleElement()

        return result
    }

    private fun isValidUpdate(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        val pageIndex = update.withIndex().associate { it.value to it.index }
        return rules.all { (before, after) ->
            when {
                before !in pageIndex || after !in pageIndex -> true // Ignore rules with missing pages
                else -> pageIndex[before]!! < pageIndex[after]!!
            }
        }
    }


}

private fun List<List<Int>>.sumOfTheirMiddleElement(): Int {
    return this.sumOf {
        val size = it.size
        assert(size > 2) {
            "$it is too short"
        }
        val middle = if ((size % 2) == 0) size / 2 else ceil(size / 2f).toInt()
        it[middle - 1]
    }
}
