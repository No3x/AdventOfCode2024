package de.no3x.adventofcode.twentyfour.day8

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day8Test {

    @Test
    fun `sample is 3749`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day8/sample.csv") input: String
    ) {
        val result = Day8().solve(input.lines())

        assertThat(result).isEqualTo(14)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day8/puzzle.csv") input: String
    ) {
        val result = Day8().solve(input.lines())

        println(result)
        assertThat(result).isNotNull()
    }

}