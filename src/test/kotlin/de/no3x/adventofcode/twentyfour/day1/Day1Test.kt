package de.no3x.adventofcode.twentyfour.day1

import de.no3x.adventofcode.support.SupportCsvFileSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest

class Day1Test {

    @ParameterizedTest
    @SupportCsvFileSource(fileName = "sample.csv")
    fun `sample data returns 11`(
        list1: List<Int>,
        list2: List<Int>
    ) {
        val distance = Day1().distance(list1, list2)

        assertThat(distance).isEqualTo(11)
    }

    @ParameterizedTest
    @SupportCsvFileSource(fileName = "puzzle.csv")
    fun `puzzle data input`(
        list1: List<Int>,
        list2: List<Int>
    ) {
        val distance = Day1().distance(list1, list2)

        println(distance)
        assertThat(distance).isGreaterThanOrEqualTo(0)
    }
}