package de.no3x.adventofcode.twentyfour.day7

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day7Test {

    @Test
    fun `sample is 3749`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day7/sample.csv") input: String
    ) {
        val result = Day7().solve(input.lines())

        assertThat(result).isEqualTo(3749)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day7/puzzle.csv") input: String
    ) {
        val result = Day7().solve(input.lines())

        println(result)
        assertThat(result).isNotNull()
    }

    @Test
    fun `puzzle data input part2`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day7/puzzle.csv") input: String
    ) {
        val result = Day7Part2().solve(input.lines())

        println(result)
        assertThat(result).isEqualTo(61561126043536)
    }
}