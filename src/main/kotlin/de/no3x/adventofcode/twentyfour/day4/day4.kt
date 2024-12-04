package de.no3x.adventofcode.twentyfour.day4

class Day4 {

    fun solve(input: String): Int {

       return 1;
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

