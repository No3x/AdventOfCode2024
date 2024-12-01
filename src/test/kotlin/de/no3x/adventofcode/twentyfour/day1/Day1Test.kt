package de.no3x.adventofcode.twentyfour.day1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1Test {

    @Test
    fun `isEmpty should return true for empty lists`() {
        val list = listOf<String>()

        assertThat(list).isEmpty()
    }
}