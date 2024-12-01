package de.no3x.adventofcode.twentyfour.day1

import kotlin.math.abs

data class LocationIdPair(val first: LocationId, val second: LocationId) {
    fun distance() : Int = first.distanceTo(second)
}

data class LocationId(val id: Int) {
    fun distanceTo(other: LocationId) : Int = abs(this.id - other.id)
}

class Day1 {

    fun distance(list1: List<Int>, list2: List<Int>): Int {

        val list1Sorted = list1.sorted().map { LocationId(it) }
        val list2Sorted = list2.sorted().map { LocationId(it) }

        val locations = list1Sorted.zip(list2Sorted) {
            a, b -> LocationIdPair(a,b)
        }

        return locations.totalDistance()
    }
}

private fun List<LocationIdPair>.totalDistance(): Int {
    return sumOf { it.distance() }
}
