package de.no3x.adventofcode.twentyfour.day4

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day4Test {

    @Test
    fun `sample data mul to 161`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day4/sample.csv") input: String
    ) {
        val result = Day4().solve(input)

        assertThat(result).isEqualTo(18)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day4/puzzle.csv") input: String
    ) {
        val result = Day4().solve(input)

        println(result)
        assertThat(result).isNotNull()
    }
}