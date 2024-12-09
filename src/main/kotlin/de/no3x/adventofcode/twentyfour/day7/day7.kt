package de.no3x.adventofcode.twentyfour.day7



class Day7 {

    fun solve(input: List<String>): Long {
        val x = input
            .flatMap { parseMuls(it) }
            .mapNotNull { bruteForce(it) }
            .sum()
        println(x)
        return x
    }

    private fun parseMuls(input: String): List<Pair<Long, List<Long>>> {
        val result = input.substringBefore(":")
        val ops = input.substringAfter(":")
        return listOf(
            result.toLong() to parseNums(ops)
        )

    }

    fun parseNums(input: String): List<Long> {
        return input.trim().split(" ").map { it.toLong() }
    }

    private fun bruteForce(input: Pair<Long, List<Long>>): Long? {
        val numbers = input.second

       return input.second.filter { allEquations(numbers.first(), numbers.drop(1)).any { input.first == it } }.map {input.first}.firstOrNull()
    }

    private fun allEquations(head: Long, list: List<Long>): List<Long> {
        return if (list.isEmpty()) {
            listOf(head)
        } else {
            allEquations(head + list.first(), list.drop(1)) +
            allEquations(head * list.first(), list.drop(1))
        }
    }
}

class Day7Part2 {

    fun solve(input: List<String>): Long {
        val x = input
            .flatMap { parseMuls(it) }
            .mapNotNull { bruteForce(it) }
            .sum()
        println(x)
        return x
    }

    private fun parseMuls(input: String): List<Pair<Long, List<Long>>> {
        val result = input.substringBefore(":")
        val ops = input.substringAfter(":")
        return listOf(
            result.toLong() to parseNums(ops)
        )

    }

    fun parseNums(input: String): List<Long> {
        return input.trim().split(" ").map { it.toLong() }
    }

    private fun bruteForce(input: Pair<Long, List<Long>>): Long? {
        val numbers = input.second

        return input.second.filter { allEquations(numbers.first(), numbers.drop(1)).any { input.first == it } }.map {input.first}.firstOrNull()
    }

    private fun allEquations(head: Long, list: List<Long>): List<Long> {
        return if (list.isEmpty()) {
            listOf(head)
        } else {
            allEquations(head + list.first(), list.drop(1)) +
                    allEquations(head * list.first(), list.drop(1)) +
                    allEquations("$head${list.first()}".toLong(), list.drop(1))
        }
    }
}