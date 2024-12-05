package de.no3x.adventofcode.twentyfour.day5

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day5Test {

    @Test
    fun `sample sum is 143`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day5/sample.csv") input: String
    ) {
        val matrix = transformInput(input)

        val result = Day5().solve(matrix)

        assertThat(result).isEqualTo(143)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day5/puzzle.csv") input: String
    ) {
        val matrix = transformInput(input)

        val result = Day5().solve(matrix)

        println(result)
        assertThat(result).isNotNull()
    }

    private fun transformInput(input: String): Input {
        var separatorHit = false
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()
        input.lines()
            .forEach { s ->
                run {
                    if (s.isEmpty()) {
                        separatorHit = true
                        return@forEach
                    }
                    if (!separatorHit) {
                        val split = s.split("|")
                        rules.add(split[0].toInt() to split[1].toInt())
                    } else {
                        val split = s.split(",").map { it.toInt() }
                        updates.add(split)
                    }
                }
            }
        return Input(rules, updates)
    }
}