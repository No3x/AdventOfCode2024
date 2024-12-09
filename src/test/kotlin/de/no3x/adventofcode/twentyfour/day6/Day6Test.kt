package de.no3x.adventofcode.twentyfour.day6

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day6Test {

    @Test
    fun `sample is 41`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day6/sample.csv") input: String
    ) {
        val result = Day6().solve(input.lines())

        assertThat(result).isEqualTo(41)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day6/puzzle.csv") input: String
    ) {
        val result = Day6().solve(input.lines())

        println(result)
        assertThat(result).isNotNull()
    }
}