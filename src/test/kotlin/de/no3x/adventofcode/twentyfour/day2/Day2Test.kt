package de.no3x.adventofcode.twentyfour.day2

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day2Test {

    @Test
    fun `sample data contains 2 safe entries`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day2/sample.csv") input: String
    ) {
        val toList = toIntMatrix(input)

        val result = Day2().solve(toList)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `sample data 1 3 2 4 5 returns false`() {

        val result = Day2().areAllPositiveOrAllNegativeAndDecreasingBetween1And3(listOf(1, 3, 2, 4, 5))

        assertThat(result).isFalse()
    }

    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day2/puzzle.csv") input: String
    ) {
        val toList = toIntMatrix(input)

        val result = Day2().solve(toList)

        println(result)
        assertThat(result).isNotNull()
    }

    private fun toIntMatrix(input: String): List<List<Int>> {
        return input.lines()
            .map { s ->
                s.split(" ").map { it.toInt() }
            }
    }
}