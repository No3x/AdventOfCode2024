package de.no3x.adventofcode.twentyfour.day15

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day15Test {

    @Test
    fun `sample is 2028`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day15/sample.csv") input: String
    ) {
        val result = Day15().solve(input)

        assertThat(result).isEqualTo(2028)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day15/puzzle.csv") input: String
    ) {
        val result = Day15().solve(input)

        println(result)
        assertThat(result).isNotNull()
    }

}