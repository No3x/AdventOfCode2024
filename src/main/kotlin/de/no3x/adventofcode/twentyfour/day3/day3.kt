package de.no3x.adventofcode.twentyfour.day3

class Day3 {

    fun solve(input: String): Int {

        var muls = parseMuls(input);
        return muls.map {
            mul -> mul.x * mul.y
        }.sum()
    }

    private fun parseMuls(input: String): List<Mul> {
        val regex = """(mul\([^\d),]?(\d+)[^),]?,[^),]?(\d+)[^\d),]?\))""".toRegex()
        val matchResults = regex.findAll(input)
        return matchResults.map {
            matchResult -> Mul(Integer.parseInt(matchResult.groups[2]!!.value), Integer.parseInt(matchResult.groups[3]!!.value))
        }.toList()
    }

}

data class Mul(val x: Int, val y: Int)

