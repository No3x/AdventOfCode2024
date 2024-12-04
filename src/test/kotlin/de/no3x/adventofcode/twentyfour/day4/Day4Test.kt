package de.no3x.adventofcode.twentyfour.day4

import io.hosuaby.inject.resources.junit.jupiter.GivenTextResource
import io.hosuaby.inject.resources.junit.jupiter.TestWithResources
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


@TestWithResources
class Day4Test {

    @Test
    fun `test rotate diagonal`() {
        val input =
            listOf(
                listOf('X', '.', '.', '.'),
                listOf('.', 'M', '.', '.'),
                listOf('.', '.', 'A', '.'),
                listOf('.', '.', '.', 'S')
            )

        val result = Day4().solve(input)

        assertThat(result).isEqualTo(1)
    }


    @Test
    fun `sample data has 18 occurrences`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day4/sample.csv") input: String
    ) {
        val matrix = toMatrix(input)

        val result = Day4().solve(matrix)

        assertThat(result).isEqualTo(18)
    }


    @Test
    fun `puzzle data input`(
        @GivenTextResource("de/no3x/adventofcode/twentyfour/day4/puzzle.csv") input: String
    ) {
        val matrix = toMatrix(input)

        val result = Day4().solve(matrix)

        println(result)
        assertThat(result).isNotNull()
    }

    private fun toMatrix(input: String): List<List<Char>> {
        return input.lines()
            .map { s ->
                s.map { it }
            }
    }
}