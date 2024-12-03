package de.no3x.adventofcode.twentyfour.day3

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day3Test {

    @Test
    fun `sample data mul to 161`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day3/sample.csv") input: String
    ) {
        val result = Day3().solve(input)

        assertThat(result).isEqualTo(161)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day3/puzzle.csv") input: String
    ) {
        val result = Day3().solve(input)

        println(result)
        assertThat(result).isNotNull()
    }
}