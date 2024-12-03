package de.no3x.adventofcode.twentyfour.day3

class Day3 {

    fun solve(input: String): Int {

        val muls = parseMuls(input)
        return muls.sumOf { mul ->
            mul.first * mul.second
        }
    }

    private fun parseMuls(input: String): List<Pair<Int, Int>> {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        return regex.findAll(input).map { matchResult ->
            matchResult.destructured.let { (first, second) ->
                first.toInt() to second.toInt()
            }
        }.toList()
    }

}

