package de.no3x.adventofcode.twentyfour.day3

class Day3 {

    fun solve(input: String): Int {

        val muls = parseMuls(input)
        return muls.map {
            mul -> mul.first * mul.second
        }.sum()
    }

    private fun parseMuls(input: String): List<Pair<Int,Int>> {
        val regex = """(mul\((\d+),(\d+)\))""".toRegex()
        val matchResults = regex.findAll(input)
        return matchResults.map {
            matchResult -> Pair(Integer.parseInt(matchResult.groups[2]!!.value), Integer.parseInt(matchResult.groups[3]!!.value))
        }.toList()
    }

}

