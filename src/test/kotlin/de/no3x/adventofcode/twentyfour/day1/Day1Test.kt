package de.no3x.adventofcode.twentyfour.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `sample data returns 11`() {

        val list1 = listOf(
            3,
            4,
            2,
            1,
            3,
            3
        )

        val list2 = listOf(
            4,
            3,
            5,
            3,
            9,
            3,
        )

        val distance = Day1().distance(list1, list2)

        assertThat(distance).isEqualTo(11)
    }
}