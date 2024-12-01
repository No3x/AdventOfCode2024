package de.no3x.adventofcode.twentyfour.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

class Day1Test {

    @ParameterizedTest
    @MethodSource("sampleData")
    fun `sample data returns 11`(
        list1: List<Int>,
        list2: List<Int>
    ) {
        val distance = Day1().distance(list1, list2)

        assertThat(distance).isEqualTo(11)
    }

    @ParameterizedTest
    @MethodSource("puzzleData")
    fun `puzzle data input`(
        list1: List<Int>,
        list2: List<Int>
    ) {
        val distance = Day1().distance(list1, list2)

        println(distance)
        assertThat(distance).isGreaterThanOrEqualTo(0)
    }

    companion object {
        @JvmStatic
        fun sampleData(): Stream<Arguments> {
            return readFromCsv("sample.csv")
        }

        @JvmStatic
        fun puzzleData(): Stream<Arguments> {
            return readFromCsv("puzzle.csv")
        }

        private fun readFromCsv(filename: String): Stream<Arguments> {
            val path = Paths.get(
                Day1Test::class.java.getResource(filename)?.toURI()
                    ?: throw IllegalArgumentException("File not found in resources: $filename")
            )
            val lines = Files.readAllLines(path)

            val column1 = mutableListOf<Int>()
            val column2 = mutableListOf<Int>()

            lines.forEach { it ->
                val (col1, col2) = it.split(",").map { it.trim().toInt() }
                column1.add(col1)
                column2.add(col2)
            }

            return Stream.of(Arguments.of(column1, column2))
        }
    }
}